package com.aissatna.medilinkbackend.util;

import com.aissatna.medilinkbackend.exception.ResourceNotFoundException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Base64Util {

	
	public static byte[] decodeBase64ToBytes(String base64String) {
		if(base64String.contains(","))
			base64String = base64String.replaceFirst("^.+,", "");
		return Base64.getDecoder().decode(base64String.getBytes(StandardCharsets.UTF_8));
	}


	public static String getFileExtensionFromBase64(String base64String) {
		if (base64String.contains(";"))
			return base64String.split(";")[0].split("/")[1];
		else
			throw new ResourceNotFoundException("The file extension cannot be founded.");
	}

}
