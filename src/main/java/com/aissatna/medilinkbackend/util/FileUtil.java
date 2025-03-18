package com.aissatna.medilinkbackend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("squid:S5443")
@Slf4j
public class FileUtil {
	public static File convertInputStreamToFile(InputStream inputStream, String fileName) {
		try {
			File tempFile = File.createTempFile(fileName.substring(0, fileName.indexOf(".")), fileName.substring(fileName.indexOf(".")));
			FileUtils.copyToFile(inputStream, tempFile);
			return tempFile;
		} catch (Exception e) {
			log.error("Error occurred while converting stream to file {}",e.getMessage());
		}
		return null;
	}
}
