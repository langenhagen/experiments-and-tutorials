#ifndef INCLUDED_KinectData_hpp
#define INCLUDED_KinectData_hpp

#include <cstdint>
#include <cstddef>

#define FRAME_NUMBER__OFFSET 0
#define FRAME_NUMBER__SIZE sizeof(std::int32_t)
#define TIMESTAMP__OFFSET (FRAME_NUMBER__OFFSET + FRAME_NUMBER__SIZE)
#define TIMESTAMP__SIZE sizeof(std::int64_t)
#define WIDTH__OFFSET (TIMESTAMP__OFFSET + TIMESTAMP__SIZE)
#define WIDTH__SIZE sizeof(std::int32_t)
#define HEIGHT__OFFSET (WIDTH__OFFSET + WIDTH__SIZE)
#define HEIGHT__SIZE sizeof(std::int32_t)
#define BYTES_PER_PIXEL__OFFSET (HEIGHT__OFFSET + HEIGHT__SIZE)
#define BYTES_PER_PIXEL__SIZE sizeof(std::uint8_t)
#define FORMAT__OFFSET (BYTES_PER_PIXEL__OFFSET + BYTES_PER_PIXEL__SIZE)
#define FORMAT__SIZE sizeof(std::uint8_t)
#define RESERVED1__OFFSET (FORMAT__OFFSET + FORMAT__SIZE)
#define RESERVED1__SIZE sizeof(std::uint8_t)
#define RESERVED2__OFFSET (RESERVED1__OFFSET + RESERVED1__SIZE)
#define RESERVED2__SIZE sizeof(std::uint8_t)
#define RESERVED3__OFFSET (RESERVED2__OFFSET + RESERVED2__SIZE)
#define RESERVED3__SIZE sizeof(std::uint8_t)

#define HEADER__SIZE (RESERVED3__OFFSET + RESERVED3__SIZE)
#define PAYLOAD__SIZE 9000000

namespace KinectData
{
	#define ColorImageType 0
	#define DepthImageType 1

	/**
	 * This struct describes a general image in the shared memory.
	 * You can gain access to its complete raw data, but also to
	 * its decrypted header and data in a struct.
	 */
    struct GeneralImage
    {
        enum Format
        {
            UNKNOWN = 0,
            RGBA32,
            BGRA32,
            RGB24,
            BGR24
        };

		union {
			uint8_t dataBytes[HEADER__SIZE + PAYLOAD__SIZE];
			struct {
				struct {
					std::int32_t frameNumber;
					std::int32_t timeStampLO;
					std::int32_t timeStampHI;
					std::int32_t width;
					std::int32_t height;
					std::int32_t bytesPerPixel;
					std::uint8_t format;
					std::uint8_t reserved1;
					std::uint8_t reserved2;
					std::uint8_t reserved3;
				} header;
				struct {
					std::uint8_t image[PAYLOAD__SIZE];
				} payload;
			} dataStruct;
		} datagram;


        std::int32_t frameNumber() const { return datagram.dataStruct.header.frameNumber; }
		void frameNumber( std::int32_t newVal ) { datagram.dataStruct.header.frameNumber = newVal; }

        std::int64_t timeStamp() const { return (datagram.dataStruct.header.timeStampHI << 32) + datagram.dataStruct.header.timeStampLO; }
		void timeStamp( std::int64_t newVal ) { datagram.dataStruct.header.timeStampHI = newVal >> 32; datagram.dataStruct.header.timeStampLO = newVal & 0xFFFF;}

        std::int32_t width() const { return datagram.dataStruct.header.width; }
		void width( std::int32_t newVal ) { datagram.dataStruct.header.width = newVal; }

        std::int32_t height() const { return datagram.dataStruct.header.height; }
		void height( std::int32_t newVal ) { datagram.dataStruct.header.height = newVal; }

        std::int32_t bytesPerPixel() const { return datagram.dataStruct.header.bytesPerPixel; }
		void bytesPerPixel( std::int32_t newVal ) { datagram.dataStruct.header.bytesPerPixel = newVal; }

        Format format() const { return static_cast<Format>(datagram.dataStruct.header.format); }
		void format( Format newVal ) { datagram.dataStruct.header.format = static_cast<uint8_t>(newVal); }

        std::size_t imageLength() const { return static_cast<std::size_t>(datagram.dataStruct.header.bytesPerPixel * datagram.dataStruct.header.width * datagram.dataStruct.header.height); }

		std::size_t metadataLength() { return sizeof(datagram.dataStruct.header); }

		std::size_t totalLength() { return sizeof(datagram.dataBytes); }
    };






} // namespace: KinectData



#undef FRAME_NUMBER__OFFSET
#undef FRAME_NUMBER__SIZE
#undef TIMESTAMP__OFFSET
#undef TIMESTAMP__SIZE
#undef WIDTH__OFFSET
#undef WIDTH__SIZE
#undef HEIGHT__OFFSET
#undef HEIGHT__SIZE
#undef BYTES_PER_PIXEL__OFFSET
#undef BYTES_PER_PIXEL__SIZE
#undef FORMAT__OFFSET
#undef FORMAT__SIZE
#undef RESERVED1__OFFSET
#undef RESERVED1__SIZE
#undef RESERVED2__OFFSET
#undef RESERVED2__SIZE
#undef RESERVED3__OFFSET
#undef RESERVED3__SIZE

#endif /* INCLUDED_KinectData_hpp */
