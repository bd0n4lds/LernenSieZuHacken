#ifdef ANDROID
#define LOG_TAG "libmei"
#include <cutils/log.h>
#define mei_msg(_me, fmt, ARGS...) ALOGV_IF(_me->verbose, fmt, ##ARGS)
#define mei_err(_me, fmt, ARGS...) ALOGE(fmt, ##ARGS)
static inline void __dump_buffer(const char *buf)
{
	ALOGV("%s\n", buf);
}

#else /* ! ANDROID */
#define mei_msg(_me, fmt, ARGS...)               \
	do                                           \
	{                                            \
		if (_me->verbose)                        \
			fprintf(stderr, "me: " fmt, ##ARGS); \
	} while (0)

#define mei_err(_me, fmt, ARGS...)                  \
	do                                              \
	{                                               \
		fprintf(stderr, "me: error: " fmt, ##ARGS); \
	} while (0)
static inline void __dump_buffer(const char *buf)
{
	fprintf(stderr, "%s\n", buf);
	;
}
#endif /* ANDROID */

static void dump_hex_buffer(const unsigned char *buf, size_t len)
{
	const size_t pbufsz = 16 * 3;
	char pbuf[pbufsz];
	int j = 0;

	while (len-- > 0)
	{
		snprintf(&pbuf[j], pbufsz - j, "%02X ", *buf++);
		j += 3;
		if (j == 16 * 3)
		{
			__dump_buffer(pbuf);
			j = 0;
		}
	}
	if (j)
		__dump_buffer(pbuf);
}
