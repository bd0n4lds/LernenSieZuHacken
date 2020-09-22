#include <array>

template <typename VT>
std::array<VT, 1> get_data(const VT &v1)
{
    std::array<VT, 1> data{v1}; /// list initialization
    return data;
}
template <typename VT>
std::array<VT, 2> get_data(const VT &v1, const VT &v2)
{
    std::array<VT, 2> data{v1, v2};
    return data;
}
template <typename VT>
std::array<VT, 3> get_data(const VT &v1, const VT &v2, const VT &v3)
{
    std::array<VT, 3> data{v1, v2, v3};
    return data;
}