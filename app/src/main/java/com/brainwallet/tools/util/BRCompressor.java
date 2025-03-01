package com.brainwallet.tools.util;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import timber.log.Timber;

public class BRCompressor {
    public static final String TAG = BRCompressor.class.getName();

    public static byte[] gZipExtract(byte[] compressed) {
        if (compressed == null || compressed.length == 0) return null;
        try {
            InputStream isr = new GZIPInputStream(new ByteArrayInputStream(compressed));
            return IOUtils.toByteArray(isr);
        } catch (IOException e) {
            Timber.e(e);
        }
        return null;
    }

    public static byte[] gZipCompress(byte[] data) {
        if (data == null) return null;
        byte[] compressedData = null;
        try {
            ByteArrayOutputStream byteStream =
                    new ByteArrayOutputStream(data.length);
            try {
                GZIPOutputStream zipStream =
                        new GZIPOutputStream(byteStream);
                try {
                    zipStream.write(data);
                } finally {
                    try {
                        zipStream.close();
                    } catch (IOException e) {
                        Timber.e(e);
                    }
                }
            } finally {
                try {
                    byteStream.close();
                } catch (IOException e) {
                    Timber.e(e);
                }
            }
            compressedData = byteStream.toByteArray();
        } catch (Exception e) {
            Timber.e(e);
        }
        return compressedData;
    }

    public static byte[] bz2Extract(byte[] compressed) {
        if (compressed == null || compressed.length == 0) return null;

        ByteArrayInputStream is = new ByteArrayInputStream(compressed);
        InputStream bin = null;
        try {
            bin = new BZip2CompressorInputStream(is);
            return IOUtils.toByteArray(bin);

        } catch (IOException e) {
            Timber.e(e);
        } finally {
            try {
                if (bin != null) {
                    bin.close();
                }
                is.close();
            } catch (IOException e) {
                Timber.e(e);
            }
        }
        return null;
    }

    public static byte[] bz2Compress(byte[] data) throws IOException {
        if (data == null) return null;
        byte[] compressedData = null;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(data.length);
        try {
            BZip2CompressorOutputStream bout = new BZip2CompressorOutputStream(byteStream);
            try {
                bout.write(data);
            } catch (Exception e) {
                throw e;
            } finally {
                try {
                    bout.close();
                } catch (IOException e) {
                    Timber.e(e);
                }
            }
            compressedData = byteStream.toByteArray();
        } finally {
            try {
                byteStream.close();
            } catch (IOException e) {
                Timber.e(e);
            }
        }
        return compressedData;

    }

    public static boolean isGZIPStream(byte[] bytes) {
        return bytes[0] == (byte) GZIPInputStream.GZIP_MAGIC
                && bytes[1] == (byte) (GZIPInputStream.GZIP_MAGIC >>> 8);
    }
}
