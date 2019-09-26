#pragma once
 
static const int ECHO_PORT = 50013;
static const char* ECHO_PORT_S = "50013";
static const char* ECHO_HOST = "localhost";
 
const int MSG_LEN = 8;
 
extern void server();
extern void client();