#include <boost/bind.hpp>
#include <boost/asio.hpp>

using boost::asio::ip::tcp;


/// The class session.
class chat_session
{
public: // constructor

    /**
     * Main Constructor
     * @param io_service The io service to be connected.
     */
    chat_session(boost::asio::io_service& io_service, const std::string& password)
    :
    socket_(io_service),
    password_(password)
    {}

public: // methods

    /**
     * Retrieves a reference to the socket member variable.
     */
    tcp::socket& socket()
    {
        return socket_;
    }

    /**
     * Retrieves the last read internal stored message.
     * @return the last read message.
     */
    std::string message()
    {
        return std::string(message_);
    }

    /**
     * Start
     */
    void start()
    {
        do_read();
        //write<();
    }


    /**
     * do_read
     */
    void do_read()
    {
        socket_.async_read_some(
            boost::asio::buffer( message_, max_msg_length),
            boost::bind(
                &chat_session::on_read,
                this, boost::asio::placeholders::error
            )
        );

        std::cout << "STYLE";
    }

    /**
     * Write
     */
    void do_write( const std::string& message )
    {
        socket_.async_write_some(
            boost::asio::buffer( message.c_str(), max_msg_length),
            boost::bind(
                &chat_session::on_write,
                this,
                boost::asio::placeholders::error
            )
        );
    }

private: // handlers

    /**
     * Handles the completion of a read message, meaning starts a new reading process
     * for continuous reading.
     * @param error The error code that is handed to this handler.
     */
    void on_read( const boost::system::error_code& error_code)
    {
        if (error_code)
        {
            delete this;
        }

        do_read();
    }


    /**
     * Handles the completion of a written message,
     * actually does nothing but close session in case of error.
     * @param error The error code that is handed to this handler.
     */
    void on_write(const boost::system::error_code& error_code)
    {
        if (error_code)
        {
            delete this;
        }
    }

private: // members

    // The socket
    tcp::socket socket_;
    enum { max_msg_length = 1024 };

    // The message.
    char message_[max_msg_length];

    /// The password to encrypt/decrypt messages
    const std::string& password_;
};
