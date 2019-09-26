#pragma once

#include <cstddef>
#include <iostream>
#include <iterator>
#include <algorithm>

template<typename T>
class my_array {
	T* data_;
	std::size_t size_;

public:

	// ---------------------------------
	// Forward declaration
	// ---------------------------------
	class const_iterator;

	// ---------------------------------
	// iterator class
	// ---------------------------------
	class iterator : public std::iterator<std::random_access_iterator_tag, T>
	{
	public:
		iterator() : p_(NULL) {}
		iterator(T* p) : p_(p) {}
		iterator(const iterator& other) : p_(other.p_) {}
		const iterator& operator=(const iterator& other) { p_ = other.p_; return other; }

		iterator& operator++() { p_++; return *this; } // prefix++
		iterator  operator++(int) { iterator tmp(*this); ++(*this); return tmp; } // postfix++
		iterator& operator--() { p_--; return *this; } // prefix--
		iterator  operator--(int) { iterator tmp(*this); --(*this); return tmp; } // postfix--

		void     operator+=(const std::size_t& n) { p_ += n; }
		void     operator+=(const iterator& other) { p_ += other.p_; }
		iterator operator+ (const std::size_t& n) { iterator tmp(*this); tmp += n; return tmp; }
		iterator operator+ (const iterator& other) { iterator tmp(*this); tmp += other; return tmp; }

		void        operator-=(const std::size_t& n) { p_ -= n; }
		void        operator-=(const iterator& other) { p_ -= other.p_; }
		iterator    operator- (const std::size_t& n) { iterator tmp(*this); tmp -= n; return tmp; }
		std::size_t operator- (const iterator& other) { return p_ - other.p_; }

		bool operator< (const iterator& other) { return (p_ - other.p_)< 0; }
		bool operator<=(const iterator& other) { return (p_ - other.p_) <= 0; }
		bool operator> (const iterator& other) { return (p_ - other.p_)> 0; }
		bool operator>=(const iterator& other) { return (p_ - other.p_) >= 0; }
		bool operator==(const iterator& other) { return  p_ == other.p_; }
		bool operator!=(const iterator& other) { return  p_ != other.p_; }

		T& operator[](const int& n) { return *(p_ + n); }
		T& operator*() { return *p_; }
		T* operator->() { return  p_; }

	private:
		T* p_;

		friend class const_iterator;
	};

	// ---------------------------------
	// const_iterator class
	// ---------------------------------
	class const_iterator : public std::iterator<std::random_access_iterator_tag, T>
	{
	public:
		const_iterator() : p_(NULL) {}
		const_iterator(const T* p) : p_(p) {}
		const_iterator(const iterator& other) : p_(other.p_) {}
		const_iterator(const const_iterator& other) : p_(other.p_) {}
		const const_iterator& operator=(const const_iterator& other) { p_ = other.p_; return other; }
		const const_iterator& operator=(const iterator& other) { p_ = other.p_; return other; }

		const_iterator& operator++() { p_++; return *this; } // prefix++
		const_iterator  operator++(int) { const_iterator tmp(*this); ++(*this); return tmp; } // postfix++
		const_iterator& operator--() { p_--; return *this; } // prefix--
		const_iterator  operator--(int) { const_iterator tmp(*this); --(*this); return tmp; } // postfix--

		void           operator+=(const std::size_t& n) { p_ += n; }
		void           operator+=(const const_iterator& other) { p_ += other.p_; }
		const_iterator operator+ (const std::size_t& n)        const { const_iterator tmp(*this); tmp += n; return tmp; }
		const_iterator operator+ (const const_iterator& other) const { const_iterator tmp(*this); tmp += other; return tmp; }

		void           operator-=(const std::size_t& n) { p_ -= n; }
		void           operator-=(const const_iterator& other) { p_ -= other.p_; }
		const_iterator operator- (const std::size_t& n)        const { const_iterator tmp(*this); tmp -= n; return tmp; }
		std::size_t    operator- (const const_iterator& other) const { return p_ - other.p_; }

		bool operator< (const const_iterator& other) const { return (p_ - other.p_)< 0; }
		bool operator<=(const const_iterator& other) const { return (p_ - other.p_) <= 0; }
		bool operator> (const const_iterator& other) const { return (p_ - other.p_)> 0; }
		bool operator>=(const const_iterator& other) const { return (p_ - other.p_) >= 0; }
		bool operator==(const const_iterator& other) const { return  p_ == other.p_; }
		bool operator!=(const const_iterator& other) const { return  p_ != other.p_; }

		const T& operator[](const int& n) const { return *(p_ + n); }
		const T& operator*()  const { return *p_; }
		const T* operator->() const { return  p_; }

	private:
		const T* p_;
	};

	my_array()
		: data_(NULL), size_(0)
	{}
	my_array(std::size_t size)
		: data_(new T[size]), size_(size)
	{}
	my_array(const my_array<T>& other) {
		size_ = other.size_;
		data_ = new T[size_];
		for (std::size_t i = 0; i<size_; i++)
			data_[i] = other.data_[i];
	}
	my_array(const const_iterator& first, const const_iterator& last) {
		size_ = last - first;
		data_ = new T[size_];
		for (std::size_t i = 0; i<size_; i++)
			data_[i] = first[i];
	}

	~my_array() {
		delete[] data_;
	}
	const my_array<T>& operator=(const my_array<T>& other) {
		size_ = other.size_;
		data_ = new T[size_];
		for (std::size_t i = 0; i<size_; i++)
			data_[i] = other.data_[i];
		return other;
	}
	const T& operator[](std::size_t idx) const { return data_[idx]; }
	T& operator[](std::size_t& idx) { return data_[idx]; }
	std::size_t size() { return size_; }

	iterator begin() { return iterator(data_); }
	iterator end() { return iterator(data_ + size_); }
	const_iterator begin() const { return const_iterator(data_); }
	const_iterator end() const { return const_iterator(data_ + size_); }
};

template<typename T>
void print(T t) {
	std::cout << t << std::endl;
}
