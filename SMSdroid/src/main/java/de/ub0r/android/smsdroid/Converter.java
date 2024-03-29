/*
 * Copyright (C) 2009-2015-2012 Lado Kumsiashvili, Felix Bechstein
 * 
 * This file is part of SMSdroid.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.ub0r.android.smsdroid;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Converter {
    private Converter() { }
    private static final Pattern PATTERN = Pattern.compile("&#([0-9]{1,7});");
    public static CharSequence convertDecNCR2Char(final CharSequence str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher m = PATTERN.matcher(str);

        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String c = m.group();
            m.appendReplacement(sb, dec2char(c.substring(2, c.length() - 1)));

        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String dec2char(final String str) {
        try {
            int n = Integer.parseInt(str);
            if (n <= 0xFFFF) {
                return String.valueOf((char) n);
            } else if (n <= 0x10FFFF) {
                n -= 0x10000;
                return (char) (0xD800 | (n >> 10))
                        + String.valueOf((char) (0xDC00 | (n & 0x3FF)));
            }
        } catch (NumberFormatException nfe) {
            return str;
        }
        return str;
    }
}
