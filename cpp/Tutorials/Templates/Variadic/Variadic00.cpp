#include <array>

/// require at least one parameter and it sets the type
template <typename VT, typename... Params>
std::array<VT, sizeof...(Params) + 1> get_data(const VT &v1, const Params &... params)
{
    return {v1, params...};
}