/**
 * @file File contains the defines and macros specially for the Chat project.
 * @author barn
 * @version 20131015
 *
 * I know that static const variables is more safe/regarded than using #define but fuck you!
 */
#pragma once

// TODO remove?
///////////////////////////////////////////////////////////////////////////////
// GENERIC DEFINES and MACROS


// TODO remove?
///////////////////////////////////////////////////////////////////////////////
// APPLICATION SPECIFIC DEFINES and MACROS

/// The standard port which is to be used if no port was specified by the user.

#define CHAT_STD_PORT "6666"

/// The standard password which is to be used if no password was specified by the user.
#define CHAT_STD_PASSWORD "GURKEGURKEGURKE"

/// The maximum length of a single message
#define CHAT_MAX_MSG_LENGTH 1024