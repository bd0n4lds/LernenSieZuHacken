#include <array>

template <typename VT>
std::array<VT, 1> get_data(const VT &v1)
{
    return {v1};
}
template <typename VT>
std::array<VT, 2> get_data(const VT &v1, const VT &v2)
{
    return {v1, v2};
}
template <typename VT>
std::array<VT, 3> get_data(const VT &v1, const VT &v2, const VT &v3)
{
    return {v1, v2, v3};
}