
#ifndef LOGGER_H
#define LOGGER_H

#include <iostream>
#include <cstdint>
#include <string>
#include <mutex>
#include <cstdio>
#include <cstdlib>

#include <vector>
#include <condition_variable>

class Logger
{
private:
    std::string filename;

    int m_LogLevel;
    int counter = 0;

public:
    Logger(const std::string &filename);
    ~Logger();

    void Open(const std::string &path);
    void Close();

    void SetLevel(int level);

    void Warn(const char *message);
    void Info(const char *message);
    void Error(const char *message);

    const int LogLevelError = 0;
    const int LogLevelWarning = 1;
    const int LogLevelInfo = 2;

    std::recursive_mutex lock;
    std::string lock_path;
};
#endif