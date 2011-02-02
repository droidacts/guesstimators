package org.xluz.droidacts.guesstimators.indoorRH;
/*
Copyright (c) 2011 Cecil Cheung
This software is released under the GNU General Public License version 2.
See, for example, "http://opensource.org/licenses/gpl-2.0.php".
*/
/**
 * @author Cecil Cheung
 * Comments, requests, bug reports go to the project page "http://code.google.com/p/guesstimators"
 */
public class indoorRH {
	/**
	 * Vapor pressure of water from 0 to 50 Celsius at integer intervals
	 * in units of kPa
	 * ref.: CRC Handbook of Chemistry and Physics, 86th/ed, section 6
	 */
	static final double Pwater[] = { 0.61129, 
		0.65716, 0.70605, 0.75813, 0.81359, 0.87260, 0.93537, 1.0021, 1.0730, 1.1482, 1.2281,
		1.3129, 1.4027, 1.4979, 1.5988, 1.7056, 1.8185, 1.9380, 2.0644, 2.1978, 2.3388,
		2.4877, 2.6447, 2.8104, 2.9850, 3.1690, 3.3629, 3.5670, 3.7818, 4.0078, 4.2455,
		4.4953, 4.7578, 5.0335, 5.3229, 5.6267, 5.9453, 6.2795, 6.6298, 6.9969, 7.3814,
		7.7840, 8.2054, 8.6463, 9.1075, 9.5898, 10.094, 10.620, 11.171, 11.745, 12.344
		};
	
	/** To estimate indoor relative humidity 
	 * @param Tout: outside temperature in Celsius
	 * @param Tin: inside temperature in Celsius
	 * @param RHout: outside relative humidity in percentage
	 * @return -1 if parameters out of range, otherwise from 0 to 100
	 */
	public static int calcRHin(int Tout, int Tin, int RHout) {
		int c=-1;
		if(Tout>=0 && Tout<=50)
			if(Tin>=0 && Tin<=50)
				if(RHout>=0 && RHout<=100) {
					c = (int)(Pwater[Tout] * RHout / Pwater[Tin] + 0.5);
					if(c>100) c = 100;
				}
		
		return c;
	}

}
