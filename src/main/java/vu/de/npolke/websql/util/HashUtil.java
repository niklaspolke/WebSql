package vu.de.npolke.websql.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Copyright 2015 Niklas Polke
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * @author Niklas Polke
 */
public class HashUtil {
	public static final String HASH_ALGORITHM = "MD5";

	public static String toMD5(final String textToHash) {
		// algorithm copied from:
		// http://www.mkyong.com/java/java-md5-hashing-example/
		String hashOfText = null;
		if (textToHash != null) {
			try {
				MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
				md.update(textToHash.getBytes());

				byte byteData[] = md.digest();

				// convert the byte to hex format method 2
				StringBuffer hexString = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					String hex = Integer.toHexString(0xff & byteData[i]);
					if (hex.length() == 1)
						hexString.append('0');
					hexString.append(hex);
				}
				hashOfText = hexString.toString();
			} catch (NoSuchAlgorithmException noalgorithmException) {
			}
		}

		return hashOfText;
	}
}