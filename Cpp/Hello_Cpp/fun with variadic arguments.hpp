template <typename... Args>
struct type_list {
    
    /// Type of the N-th template argument
    template <std::size_t N>
    using type = typename std::tuple_element<N, std::tuple<Args...>>::type;

    /// Number of template arguments
    static const int n_args = sizeof...(Args);

};