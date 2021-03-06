package com.cjx.websocket.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


import org.apache.commons.codec.binary.Base64;


/**
 * @author:蔡佳新
 * @date:创建时间 10:59 2018/7/31
 * @note:
 */
public class MessageDigestUtil {
        /**
         * 先进行MD5摘要再进行Base64编码获取摘要字符串
         *
         * @param str
         * @return
         */
        public static String base64AndMD5(String str) {
            if (str == null) {
                throw new IllegalArgumentException("inStr can not be null");
            }
            return base64AndMD5(toBytes(str));
        }

        /**
         * 先进行MD5摘要再进行Base64编码获取摘要字符串
         *
         * @return
         */
        public static String base64AndMD5(byte[] bytes) {
            if (bytes == null) {
                throw new IllegalArgumentException("bytes can not be null");
            }
            try {
                final MessageDigest md = MessageDigest.getInstance("MD5");
                md.reset();
                md.update(bytes);
                final Base64 base64 = new Base64();
                final byte[] enbytes = base64.encode(md.digest());
                return new String(enbytes);
            } catch (final NoSuchAlgorithmException e) {
                throw new IllegalArgumentException("unknown algorithm MD5");
            }
        }

        /**
         * UTF-8编码转换为ISO-9959-1
         *
         * @param str
         * @return
         */
        public static String utf8ToIso88591(String str) {
            if (str == null) {
                return str;
            }

            try {
                return new String(str.getBytes("UTF-8"), "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        /**
         * ISO-9959-1编码转换为UTF-8
         *
         * @param str
         * @return
         */
        public static String iso88591ToUtf8(String str) {
            if (str == null) {
                return str;
            }

            try {
                return new String(str.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        /**
         * String转换为字节数组
         *
         * @param str
         * @return
         */
        private static byte[] toBytes(final String str) {
            return  Optional.ofNullable(str)
                    .map(item->str.getBytes())
                    .orElse(null);
        }

}
