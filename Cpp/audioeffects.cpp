/** @file audioeffects.cpp
 * @brief Implementation for AudioEffect.hpp.
 * @version: 2021-02-07
 */
#include "AudioEffect.hpp"

namespace challenge {

NoiseGate::NoiseGate(const float threshold) : m_threshold(threshold) {}

void NoiseGate::process(float* buf, size_t num)
{
    for(size_t i = 0; i < num; ++i)
    {
        if(buf[i] < this->m_threshold)
        {
            *buf = 0;
        }
    }
}

Compressor::Compressor(const float threshold) : m_threshold(threshold) {}

void Compressor::process(float* buf, size_t num)
{
    for(size_t i = 0; i < num; ++i)
    {
        if(buf[i] > this->m_threshold)
        {
            *buf = this->m_threshold;
        }
    }
}

bool detect_feedback(const std::shared_ptr<AudioEffect> first)
{
    auto slow_ptr = first;
    auto fast_ptr = first;

    while(slow_ptr && fast_ptr && fast_ptr->next)
    {
        slow_ptr = slow_ptr->next;
        fast_ptr = fast_ptr->next->next;
        if(slow_ptr == fast_ptr)
        {
            return true;
        }
    }
    return false;
}

} // namespace challenge
