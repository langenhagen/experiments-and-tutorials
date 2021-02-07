/** @file audioeffects.hpp
 * @brief Contains the abstract class `AudioEffect` and some of its subclasses for the 2021 Native
 *     Instruments Coding Challenge.
 * @version: 2021-02-07
 */
#pragma once

#include <memory>

namespace challenge {

/**AudioEffect is the base class for effects that can process
 * audio and have a subsequent effect (next).
 */
struct AudioEffect
{
    /**Default destructor.*/
    virtual ~AudioEffect() = default;

    /**Process the given audio buffer of the given size.
     *
     * Consider making the function const.
     *
     * @param buf The audio buffer.
     * @param num The audio buffer size. Consider making it const.
     */
    virtual void process(float* buf, size_t num) = 0;

    /**Pointer to the next AudioEffect in an effect chain.*/
    std::shared_ptr<AudioEffect> next;
};

/**An effect that cuts off noise below a given threshold.*/
struct NoiseGate : public AudioEffect
{
    /**Constructor.
     * @param threshold The minimum value to pass the noise gate.
     */
    NoiseGate(const float threshold);

    void process(float* buf, size_t num);

    float m_threshold;
};

/**An effect that reduces high values to a certain threshold.*/
struct Compressor : public AudioEffect
{
    /**Constructor.
     * @param threshold The value above which to attenuate the values to the threshold.
     */
    Compressor(const float threshold);

    void process(float* buf, size_t num);

    float m_threshold;
};

/**Check if there is a feedback loop in a given effects chain via
 * Floyd's cycle-finding algorithm.
 * @param first The first AudioEffect in an effect chain.
 * @return Return true in case there is a feedback loop, otherwise return false.
 */
bool detect_feedback(const std::shared_ptr<AudioEffect> first);

} // namespace challenge
