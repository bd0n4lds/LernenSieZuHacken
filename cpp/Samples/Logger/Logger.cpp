
#include <cstdio>
#include <cstdlib>
#include <cassert>

#include <fcntl.h>
#include <unistd.h>

#include "Logger.h"

Logger::Logger(const std::string &afilename) : filename(afilename)
{
    int err = 0;
}

Logger::~Logger()
{
}

void Logger::Open(const std::string &path)
{
    int err = 0;
    std::lock_guard<std::recursive_mutex> guard(lock);

    //lock file
    lock_path = util::string_format("%s/%s.lock", path.c_str(), filename.c_str());
}

void Logger::Close()
{
    int err = 0;
    std::lock_guard<std::recursive_mutex> guard(lock);
}

void Logger::SetLevel(int level)
{
    m_LogLevel = level;
}

void Logger::Warn(const char *message)
{
    if (m_LogLevel >= LogLevelWarning)
        std::cout << "[WARNING]: " << message << std::endl;
}

void Logger::Info(const char *message)
{
    if (m_LogLevel >= LogLevelInfo)
        std::cout << "[INFO]: " << message << std::endl;
}

void Logger::Error(const char *message)
{
    if (m_LogLevel >= LogLevelError)
        std::cout << "[ERROR]: " << message << std::endl;
}