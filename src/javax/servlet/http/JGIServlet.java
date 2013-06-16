//
// jhttpd Applet Web server - http://www.dbexplorer.com
// Copyright (C) 2007 Dynamic Browser Applications Ltd.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//

package javax.servlet.http;

public abstract class JGIServlet extends JGICore {
	
    public JGIServlet() { super(); }

//AUTO
public final void print () {
    out.flush();
}
public final static String [] attr () {
    return new String [] {};
}
public final void print (String str1) {
    if ( out == null )
	print( header() );
    out.print( str1 );
}
public final static String [] attr (String s1) {
    return new String [] {s1};
}
public final void print (String str1, String str2) {
    print( str1+str2 );
}
public final static String [] attr (String s1, String s2) {
    return new String [] {s1,s2};
}
public final void print (String str1, String str2, String str3) {
    print( str1+str2+str3 );
}
public final static String [] attr (String s1, String s2, String s3) {
    return new String [] {s1,s2,s3};
}
public final void print (String str1, String str2, String str3, String str4) {
    print( str1+str2+str3+str4 );
}
public final static String [] attr (String s1, String s2, String s3, String s4) {
    return new String [] {s1,s2,s3,s4};
}
public final void print (String str1, String str2, String str3, String str4, String str5) {
    print( str1+str2+str3+str4+str5 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5) {
    return new String [] {s1,s2,s3,s4,s5};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6) {
    print( str1+str2+str3+str4+str5+str6 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6) {
    return new String [] {s1,s2,s3,s4,s5,s6};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7) {
    print( str1+str2+str3+str4+str5+str6+str7 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
    print( str1+str2+str3+str4+str5+str6+str7+str8 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24+str25 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24+str25+str26 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24+str25+str26+str27 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26, String s27) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24+str25+str26+str27+str28 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26, String s27, String s28) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24+str25+str26+str27+str28+str29 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26, String s27, String s28, String s29) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24+str25+str26+str27+str28+str29+str30 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26, String s27, String s28, String s29, String s30) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30};
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26, String s27, String s28, String s29, String s30, String s31, String s32, String s33, String s34, String s35, String s36, String s37, String s38, String s39, String s40, String s41, String s42, String s43, String s44, String s45, String s46) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41,s42,s43,s44,s45,s46};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24+str25+str26+str27+str28+str29+str30+str31+str32+str33+str34+str35+str36+str37+str38+str39+str40+str41+str42+str43+str44+str45+str46+str47 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26, String s27, String s28, String s29, String s30, String s31, String s32, String s33, String s34, String s35, String s36, String s37, String s38, String s39, String s40, String s41, String s42, String s43, String s44, String s45, String s46, String s47) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41,s42,s43,s44,s45,s46,s47};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24+str25+str26+str27+str28+str29+str30+str31+str32+str33+str34+str35+str36+str37+str38+str39+str40+str41+str42+str43+str44+str45+str46+str47+str48 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26, String s27, String s28, String s29, String s30, String s31, String s32, String s33, String s34, String s35, String s36, String s37, String s38, String s39, String s40, String s41, String s42, String s43, String s44, String s45, String s46, String s47, String s48) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41,s42,s43,s44,s45,s46,s47,s48};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24+str25+str26+str27+str28+str29+str30+str31+str32+str33+str34+str35+str36+str37+str38+str39+str40+str41+str42+str43+str44+str45+str46+str47+str48+str49 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26, String s27, String s28, String s29, String s30, String s31, String s32, String s33, String s34, String s35, String s36, String s37, String s38, String s39, String s40, String s41, String s42, String s43, String s44, String s45, String s46, String s47, String s48, String s49) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41,s42,s43,s44,s45,s46,s47,s48,s49};
}
public final void print (String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, String str34, String str35, String str36, String str37, String str38, String str39, String str40, String str41, String str42, String str43, String str44, String str45, String str46, String str47, String str48, String str49, String str50) {
    print( str1+str2+str3+str4+str5+str6+str7+str8+str9+str10+str11+str12+str13+str14+str15+str16+str17+str18+str19+str20+str21+str22+str23+str24+str25+str26+str27+str28+str29+str30+str31+str32+str33+str34+str35+str36+str37+str38+str39+str40+str41+str42+str43+str44+str45+str46+str47+str48+str49+str50 );
}
public final static String [] attr (String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16, String s17, String s18, String s19, String s20, String s21, String s22, String s23, String s24, String s25, String s26, String s27, String s28, String s29, String s30, String s31, String s32, String s33, String s34, String s35, String s36, String s37, String s38, String s39, String s40, String s41, String s42, String s43, String s44, String s45, String s46, String s47, String s48, String s49, String s50) {
    return new String [] {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41,s42,s43,s44,s45,s46,s47,s48,s49,s50};
}

public final static String Link () { return "<Link>"; }
public final static String Link (String str) { return "\n<Link>"+str+"</Link>"; }
public final static String Link (String attr, String str) { return "\n<Link "+attr+">"+str+"</Link>"; }
public final static String Link (String[] attr) { return tag( "Link", attr, null ); }
public final static String Link (String[] attr, String str) { return tag( "Link", attr, null )+str+"</Link>"; }
public final static String end_Link () { return "</Link>"; }
  

public final static String Param () { return "<Param>"; }
public final static String Param (String str) { return "\n<Param>"+str+"</Param>"; }
public final static String Param (String attr, String str) { return "\n<Param "+attr+">"+str+"</Param>"; }
public final static String Param (String[] attr) { return tag( "Param", attr, null ); }
public final static String Param (String[] attr, String str) { return tag( "Param", attr, null )+str+"</Param>"; }
public final static String end_Param () { return "</Param>"; }
  

public final static String Select () { return "<Select>"; }
public final static String Select (String str) { return "\n<Select>"+str+"</Select>"; }
public final static String Select (String attr, String str) { return "\n<Select "+attr+">"+str+"</Select>"; }
public final static String Select (String[] attr) { return tag( "Select", attr, null ); }
public final static String Select (String[] attr, String str) { return tag( "Select", attr, null )+str+"</Select>"; }
public final static String end_Select () { return "</Select>"; }
  

public final static String Sub () { return "<Sub>"; }
public final static String Sub (String str) { return "\n<Sub>"+str+"</Sub>"; }
public final static String Sub (String attr, String str) { return "\n<Sub "+attr+">"+str+"</Sub>"; }
public final static String Sub (String[] attr) { return tag( "Sub", attr, null ); }
public final static String Sub (String[] attr, String str) { return tag( "Sub", attr, null )+str+"</Sub>"; }
public final static String end_Sub () { return "</Sub>"; }
  

public final static String TR () { return "<tr>"; }
public final static String TR (String str) { return "\n<tr>"+str+"</tr>"; }
public final static String TR (String attr, String str) { return "\n<tr "+attr+">"+str+"</tr>"; }
public final static String TR (String[] attr) { return tag( "tr", attr, null ); }
public final static String TR (String[] attr, String str) { return tag( "tr", attr, null )+str+"</tr>"; }
public final static String end_TR () { return "</tr>"; }


public final static String Tr () { return "<Tr>"; }
public final static String Tr (String str) { return "\n<Tr>"+str+"</Tr>"; }
public final static String Tr (String attr, String str) { return "\n<Tr "+attr+">"+str+"</Tr>"; }
public final static String Tr (String[] attr) { return tag( "Tr", attr, null ); }
public final static String Tr (String[] attr, String str) { return tag( "Tr", attr, null )+str+"</Tr>"; }
public final static String end_Tr () { return "</Tr>"; }
  

public final static String a () { return "<a>"; }
public final static String a (String str) { return "\n<a>"+str+"</a>"; }
public final static String a (String attr, String str) { return "\n<a "+attr+">"+str+"</a>"; }
public final static String a (String[] attr) { return tag( "a", attr, null ); }
public final static String a (String[] attr, String str) { return tag( "a", attr, null )+str+"</a>"; }
public final static String end_a () { return "</a>"; }
  

public final static String address () { return "<address>"; }
public final static String address (String str) { return "\n<address>"+str+"</address>"; }
public final static String address (String attr, String str) { return "\n<address "+attr+">"+str+"</address>"; }
public final static String address (String[] attr) { return tag( "address", attr, null ); }
public final static String address (String[] attr, String str) { return tag( "address", attr, null )+str+"</address>"; }
public final static String end_address () { return "</address>"; }
  

public final static String applet () { return "<applet>"; }
public final static String applet (String str) { return "\n<applet>"+str+"</applet>"; }
public final static String applet (String attr, String str) { return "\n<applet "+attr+">"+str+"</applet>"; }
public final static String applet (String[] attr) { return tag( "applet", attr, null ); }
public final static String applet (String[] attr, String str) { return tag( "applet", attr, null )+str+"</applet>"; }
public final static String end_applet () { return "</applet>"; }
  

public final static String b () { return "<b>"; }
public final static String b (String str) { return "\n<b>"+str+"</b>"; }
public final static String b (String attr, String str) { return "\n<b "+attr+">"+str+"</b>"; }
public final static String b (String[] attr) { return tag( "b", attr, null ); }
public final static String b (String[] attr, String str) { return tag( "b", attr, null )+str+"</b>"; }
public final static String end_b () { return "</b>"; }
  

public final static String base () { return "<base>"; }
public final static String base (String str) { return "\n<base>"+str+"</base>"; }
public final static String base (String attr, String str) { return "\n<base "+attr+">"+str+"</base>"; }
public final static String base (String[] attr) { return tag( "base", attr, null ); }
public final static String base (String[] attr, String str) { return tag( "base", attr, null )+str+"</base>"; }
public final static String end_base () { return "</base>"; }
  

public final static String basefont () { return "<basefont>"; }
public final static String basefont (String str) { return "\n<basefont>"+str+"</basefont>"; }
public final static String basefont (String attr, String str) { return "\n<basefont "+attr+">"+str+"</basefont>"; }
public final static String basefont (String[] attr) { return tag( "basefont", attr, null ); }
public final static String basefont (String[] attr, String str) { return tag( "basefont", attr, null )+str+"</basefont>"; }
public final static String end_basefont () { return "</basefont>"; }
  

public final static String big () { return "<big>"; }
public final static String big (String str) { return "\n<big>"+str+"</big>"; }
public final static String big (String attr, String str) { return "\n<big "+attr+">"+str+"</big>"; }
public final static String big (String[] attr) { return tag( "big", attr, null ); }
public final static String big (String[] attr, String str) { return tag( "big", attr, null )+str+"</big>"; }
public final static String end_big () { return "</big>"; }
  

public final static String blockquote () { return "<blockquote>"; }
public final static String blockquote (String str) { return "\n<blockquote>"+str+"</blockquote>"; }
public final static String blockquote (String attr, String str) { return "\n<blockquote "+attr+">"+str+"</blockquote>"; }
public final static String blockquote (String[] attr) { return tag( "blockquote", attr, null ); }
public final static String blockquote (String[] attr, String str) { return tag( "blockquote", attr, null )+str+"</blockquote>"; }
public final static String end_blockquote () { return "</blockquote>"; }
  

public final static String body () { return "<body>"; }
public final static String body (String str) { return "\n<body>"+str+"</body>"; }
public final static String body (String attr, String str) { return "\n<body "+attr+">"+str+"</body>"; }
public final static String body (String[] attr) { return tag( "body", attr, null ); }
public final static String body (String[] attr, String str) { return tag( "body", attr, null )+str+"</body>"; }
public final static String end_body () { return "</body>"; }
  

public final static String br () { return "<br>"; }
public final static String br (String str) { return "\n<br>"+str+"</br>"; }
public final static String br (String attr, String str) { return "\n<br "+attr+">"+str+"</br>"; }
public final static String br (String[] attr) { return tag( "br", attr, null ); }
public final static String br (String[] attr, String str) { return tag( "br", attr, null )+str+"</br>"; }
public final static String end_br () { return "</br>"; }
  

public final static String button () { return "<button>"; }
public final static String button (String str) { return "\n<button>"+str+"</button>"; }
public final static String button (String attr, String str) { return "\n<button "+attr+">"+str+"</button>"; }
public final static String button (String[] attr) { return tag( "button", attr, null ); }
public final static String button (String[] attr, String str) { return tag( "button", attr, null )+str+"</button>"; }
public final static String end_button () { return "</button>"; }
  

public final static String caption () { return "<caption>"; }
public final static String caption (String str) { return "\n<caption>"+str+"</caption>"; }
public final static String caption (String attr, String str) { return "\n<caption "+attr+">"+str+"</caption>"; }
public final static String caption (String[] attr) { return tag( "caption", attr, null ); }
public final static String caption (String[] attr, String str) { return tag( "caption", attr, null )+str+"</caption>"; }
public final static String end_caption () { return "</caption>"; }
  

public final static String charset () { return "<charset>"; }
public final static String charset (String str) { return "\n<charset>"+str+"</charset>"; }
public final static String charset (String attr, String str) { return "\n<charset "+attr+">"+str+"</charset>"; }
public final static String charset (String[] attr) { return tag( "charset", attr, null ); }
public final static String charset (String[] attr, String str) { return tag( "charset", attr, null )+str+"</charset>"; }
public final static String end_charset () { return "</charset>"; }
  

public final static String cite () { return "<cite>"; }
public final static String cite (String str) { return "\n<cite>"+str+"</cite>"; }
public final static String cite (String attr, String str) { return "\n<cite "+attr+">"+str+"</cite>"; }
public final static String cite (String[] attr) { return tag( "cite", attr, null ); }
public final static String cite (String[] attr, String str) { return tag( "cite", attr, null )+str+"</cite>"; }
public final static String end_cite () { return "</cite>"; }
  

public final static String code () { return "<code>"; }
public final static String code (String str) { return "\n<code>"+str+"</code>"; }
public final static String code (String attr, String str) { return "\n<code "+attr+">"+str+"</code>"; }
public final static String code (String[] attr) { return tag( "code", attr, null ); }
public final static String code (String[] attr, String str) { return tag( "code", attr, null )+str+"</code>"; }
public final static String end_code () { return "</code>"; }
  

public final static String comment () { return "<comment>"; }
public final static String comment (String str) { return "\n<comment>"+str+"</comment>"; }
public final static String comment (String attr, String str) { return "\n<comment "+attr+">"+str+"</comment>"; }
public final static String comment (String[] attr) { return tag( "comment", attr, null ); }
public final static String comment (String[] attr, String str) { return tag( "comment", attr, null )+str+"</comment>"; }
public final static String end_comment () { return "</comment>"; }
  

public final static String dd () { return "<dd>"; }
public final static String dd (String str) { return "\n<dd>"+str+"</dd>"; }
public final static String dd (String attr, String str) { return "\n<dd "+attr+">"+str+"</dd>"; }
public final static String dd (String[] attr) { return tag( "dd", attr, null ); }
public final static String dd (String[] attr, String str) { return tag( "dd", attr, null )+str+"</dd>"; }
public final static String end_dd () { return "</dd>"; }
  

public final static String dfn () { return "<dfn>"; }
public final static String dfn (String str) { return "\n<dfn>"+str+"</dfn>"; }
public final static String dfn (String attr, String str) { return "\n<dfn "+attr+">"+str+"</dfn>"; }
public final static String dfn (String[] attr) { return tag( "dfn", attr, null ); }
public final static String dfn (String[] attr, String str) { return tag( "dfn", attr, null )+str+"</dfn>"; }
public final static String end_dfn () { return "</dfn>"; }
  

public final static String div () { return "<div>"; }
public final static String div (String str) { return "\n<div>"+str+"</div>"; }
public final static String div (String attr, String str) { return "\n<div "+attr+">"+str+"</div>"; }
public final static String div (String[] attr) { return tag( "div", attr, null ); }
public final static String div (String[] attr, String str) { return tag( "div", attr, null )+str+"</div>"; }
public final static String end_div () { return "</div>"; }
  

public final static String dl () { return "<dl>"; }
public final static String dl (String str) { return "\n<dl>"+str+"</dl>"; }
public final static String dl (String attr, String str) { return "\n<dl "+attr+">"+str+"</dl>"; }
public final static String dl (String[] attr) { return tag( "dl", attr, null ); }
public final static String dl (String[] attr, String str) { return tag( "dl", attr, null )+str+"</dl>"; }
public final static String end_dl () { return "</dl>"; }
  

public final static String dt () { return "<dt>"; }
public final static String dt (String str) { return "\n<dt>"+str+"</dt>"; }
public final static String dt (String attr, String str) { return "\n<dt "+attr+">"+str+"</dt>"; }
public final static String dt (String[] attr) { return tag( "dt", attr, null ); }
public final static String dt (String[] attr, String str) { return tag( "dt", attr, null )+str+"</dt>"; }
public final static String end_dt () { return "</dt>"; }
  

public final static String em () { return "<em>"; }
public final static String em (String str) { return "\n<em>"+str+"</em>"; }
public final static String em (String attr, String str) { return "\n<em "+attr+">"+str+"</em>"; }
public final static String em (String[] attr) { return tag( "em", attr, null ); }
public final static String em (String[] attr, String str) { return tag( "em", attr, null )+str+"</em>"; }
public final static String end_em () { return "</em>"; }
  

public final static String embed () { return "<embed>"; }
public final static String embed (String str) { return "\n<embed>"+str+"</embed>"; }
public final static String embed (String attr, String str) { return "\n<embed "+attr+">"+str+"</embed>"; }
public final static String embed (String[] attr) { return tag( "embed", attr, null ); }
public final static String embed (String[] attr, String str) { return tag( "embed", attr, null )+str+"</embed>"; }
public final static String end_embed () { return "</embed>"; }
  

public final static String end_form () { return "</form>"; }
public final static String end_form (String str) { return "\n</form>"+str+"<//form>"; }
public final static String end_form (String attr, String str) { return "\n</form "+attr+">"+str+"<//form>"; }
public final static String end_form (String[] attr) { return tag( "/form", attr, null ); }
public final static String end_form (String[] attr, String str) { return tag( "/form", attr, null )+str+"<//form>"; }
public final static String end_end_form () { return "</end_form>"; }
  

public final static String font () { return "<font>"; }
public final static String font (String str) { return "\n<font>"+str+"</font>"; }
public final static String font (String attr, String str) { return "\n<font "+attr+">"+str+"</font>"; }
public final static String font (String[] attr) { return tag( "font", attr, null ); }
public final static String font (String[] attr, String str) { return tag( "font", attr, null )+str+"</font>"; }
public final static String end_font () { return "</font>"; }
  

public final static String frame () { return "<frame>"; }
public final static String frame (String str) { return "\n<frame>"+str+"</frame>"; }
public final static String frame (String attr, String str) { return "\n<frame "+attr+">"+str+"</frame>"; }
public final static String frame (String[] attr) { return tag( "frame", attr, null ); }
public final static String frame (String[] attr, String str) { return tag( "frame", attr, null )+str+"</frame>"; }
public final static String end_frame () { return "</frame>"; }
  

public final static String frameset () { return "<frameset>"; }
public final static String frameset (String str) { return "\n<frameset>"+str+"</frameset>"; }
public final static String frameset (String attr, String str) { return "\n<frameset "+attr+">"+str+"</frameset>"; }
public final static String frameset (String[] attr) { return tag( "frameset", attr, null ); }
public final static String frameset (String[] attr, String str) { return tag( "frameset", attr, null )+str+"</frameset>"; }
public final static String end_frameset () { return "</frameset>"; }
  

public final static String h1 () { return "<h1>"; }
public final static String h1 (String str) { return "\n<h1>"+str+"</h1>"; }
public final static String h1 (String attr, String str) { return "\n<h1 "+attr+">"+str+"</h1>"; }
public final static String h1 (String[] attr) { return tag( "h1", attr, null ); }
public final static String h1 (String[] attr, String str) { return tag( "h1", attr, null )+str+"</h1>"; }
public final static String end_h1 () { return "</h1>"; }
  

public final static String h2 () { return "<h2>"; }
public final static String h2 (String str) { return "\n<h2>"+str+"</h2>"; }
public final static String h2 (String attr, String str) { return "\n<h2 "+attr+">"+str+"</h2>"; }
public final static String h2 (String[] attr) { return tag( "h2", attr, null ); }
public final static String h2 (String[] attr, String str) { return tag( "h2", attr, null )+str+"</h2>"; }
public final static String end_h2 () { return "</h2>"; }
  

public final static String h3 () { return "<h3>"; }
public final static String h3 (String str) { return "\n<h3>"+str+"</h3>"; }
public final static String h3 (String attr, String str) { return "\n<h3 "+attr+">"+str+"</h3>"; }
public final static String h3 (String[] attr) { return tag( "h3", attr, null ); }
public final static String h3 (String[] attr, String str) { return tag( "h3", attr, null )+str+"</h3>"; }
public final static String end_h3 () { return "</h3>"; }
  

public final static String h4 () { return "<h4>"; }
public final static String h4 (String str) { return "\n<h4>"+str+"</h4>"; }
public final static String h4 (String attr, String str) { return "\n<h4 "+attr+">"+str+"</h4>"; }
public final static String h4 (String[] attr) { return tag( "h4", attr, null ); }
public final static String h4 (String[] attr, String str) { return tag( "h4", attr, null )+str+"</h4>"; }
public final static String end_h4 () { return "</h4>"; }
  

public final static String h5 () { return "<h5>"; }
public final static String h5 (String str) { return "\n<h5>"+str+"</h5>"; }
public final static String h5 (String attr, String str) { return "\n<h5 "+attr+">"+str+"</h5>"; }
public final static String h5 (String[] attr) { return tag( "h5", attr, null ); }
public final static String h5 (String[] attr, String str) { return tag( "h5", attr, null )+str+"</h5>"; }
public final static String end_h5 () { return "</h5>"; }
  

public final static String h6 () { return "<h6>"; }
public final static String h6 (String str) { return "\n<h6>"+str+"</h6>"; }
public final static String h6 (String attr, String str) { return "\n<h6 "+attr+">"+str+"</h6>"; }
public final static String h6 (String[] attr) { return tag( "h6", attr, null ); }
public final static String h6 (String[] attr, String str) { return tag( "h6", attr, null )+str+"</h6>"; }
public final static String end_h6 () { return "</h6>"; }
  

public final static String head () { return "<head>"; }
public final static String head (String str) { return "\n<head>"+str+"</head>"; }
public final static String head (String attr, String str) { return "\n<head "+attr+">"+str+"</head>"; }
public final static String head (String[] attr) { return tag( "head", attr, null ); }
public final static String head (String[] attr, String str) { return tag( "head", attr, null )+str+"</head>"; }
public final static String end_head () { return "</head>"; }
  

public final static String hr () { return "<hr>"; }
public final static String hr (String str) { return "\n<hr>"+str+"</hr>"; }
public final static String hr (String attr, String str) { return "\n<hr "+attr+">"+str+"</hr>"; }
public final static String hr (String[] attr) { return tag( "hr", attr, null ); }
public final static String hr (String[] attr, String str) { return tag( "hr", attr, null )+str+"</hr>"; }
public final static String end_hr () { return "</hr>"; }
  

public final static String html () { return "<html>"; }
public final static String html (String str) { return "\n<html>"+str+"</html>"; }
public final static String html (String attr, String str) { return "\n<html "+attr+">"+str+"</html>"; }
public final static String html (String[] attr) { return tag( "html", attr, null ); }
public final static String html (String[] attr, String str) { return tag( "html", attr, null )+str+"</html>"; }
public final static String end_html () { return "</html>"; }
  

public final static String i () { return "<i>"; }
public final static String i (String str) { return "\n<i>"+str+"</i>"; }
public final static String i (String attr, String str) { return "\n<i "+attr+">"+str+"</i>"; }
public final static String i (String[] attr) { return tag( "i", attr, null ); }
public final static String i (String[] attr, String str) { return tag( "i", attr, null )+str+"</i>"; }
public final static String end_i () { return "</i>"; }
  

public final static String ilayer () { return "<ilayer>"; }
public final static String ilayer (String str) { return "\n<ilayer>"+str+"</ilayer>"; }
public final static String ilayer (String attr, String str) { return "\n<ilayer "+attr+">"+str+"</ilayer>"; }
public final static String ilayer (String[] attr) { return tag( "ilayer", attr, null ); }
public final static String ilayer (String[] attr, String str) { return tag( "ilayer", attr, null )+str+"</ilayer>"; }
public final static String end_ilayer () { return "</ilayer>"; }
  

public final static String img () { return "<img>"; }
public final static String img (String str) { return "\n<img>"+str+"</img>"; }
public final static String img (String attr, String str) { return "\n<img "+attr+">"+str+"</img>"; }
public final static String img (String[] attr) { return tag( "img", attr, null ); }
public final static String img (String[] attr, String str) { return tag( "img", attr, null )+str+"</img>"; }
public final static String end_img () { return "</img>"; }
  

public final static String input () { return "<input>"; }
public final static String input (String str) { return "\n<input>"+str+"</input>"; }
public final static String input (String attr, String str) { return "\n<input "+attr+">"+str+"</input>"; }
public final static String input (String[] attr) { return tag( "input", attr, null ); }
public final static String input (String[] attr, String str) { return tag( "input", attr, null )+str+"</input>"; }
public final static String end_input () { return "</input>"; }


public final static String kbd () { return "<kbd>"; }
public final static String kbd (String str) { return "\n<kbd>"+str+"</kbd>"; }
public final static String kbd (String attr, String str) { return "\n<kbd "+attr+">"+str+"</kbd>"; }
public final static String kbd (String[] attr) { return tag( "kbd", attr, null ); }
public final static String kbd (String[] attr, String str) { return tag( "kbd", attr, null )+str+"</kbd>"; }
public final static String end_kbd () { return "</kbd>"; }
  

public final static String layer () { return "<layer>"; }
public final static String layer (String str) { return "\n<layer>"+str+"</layer>"; }
public final static String layer (String attr, String str) { return "\n<layer "+attr+">"+str+"</layer>"; }
public final static String layer (String[] attr) { return tag( "layer", attr, null ); }
public final static String layer (String[] attr, String str) { return tag( "layer", attr, null )+str+"</layer>"; }
public final static String end_layer () { return "</layer>"; }
  

public final static String li () { return "<li>"; }
public final static String li (String str) { return "\n<li>"+str+"</li>"; }
public final static String li (String attr, String str) { return "\n<li "+attr+">"+str+"</li>"; }
public final static String li (String[] attr) { return tag( "li", attr, null ); }
public final static String li (String[] attr, String str) { return tag( "li", attr, null )+str+"</li>"; }
public final static String end_li () { return "</li>"; }
  

public final static String menu () { return "<menu>"; }
public final static String menu (String str) { return "\n<menu>"+str+"</menu>"; }
public final static String menu (String attr, String str) { return "\n<menu "+attr+">"+str+"</menu>"; }
public final static String menu (String[] attr) { return tag( "menu", attr, null ); }
public final static String menu (String[] attr, String str) { return tag( "menu", attr, null )+str+"</menu>"; }
public final static String end_menu () { return "</menu>"; }
  

public final static String meta () { return "<meta>"; }
public final static String meta (String str) { return "\n<meta>"+str+"</meta>"; }
public final static String meta (String attr, String str) { return "\n<meta "+attr+">"+str+"</meta>"; }
public final static String meta (String[] attr) { return tag( "meta", attr, null ); }
public final static String meta (String[] attr, String str) { return tag( "meta", attr, null )+str+"</meta>"; }
public final static String end_meta () { return "</meta>"; }
  

public final static String nextid () { return "<nextid>"; }
public final static String nextid (String str) { return "\n<nextid>"+str+"</nextid>"; }
public final static String nextid (String attr, String str) { return "\n<nextid "+attr+">"+str+"</nextid>"; }
public final static String nextid (String[] attr) { return tag( "nextid", attr, null ); }
public final static String nextid (String[] attr, String str) { return tag( "nextid", attr, null )+str+"</nextid>"; }
public final static String end_nextid () { return "</nextid>"; }
  

public final static String ol () { return "<ol>"; }
public final static String ol (String str) { return "\n<ol>"+str+"</ol>"; }
public final static String ol (String attr, String str) { return "\n<ol "+attr+">"+str+"</ol>"; }
public final static String ol (String[] attr) { return tag( "ol", attr, null ); }
public final static String ol (String[] attr, String str) { return tag( "ol", attr, null )+str+"</ol>"; }
public final static String end_ol () { return "</ol>"; }
  

public final static String option () { return "<option>"; }
public final static String option (String str) { return "\n<option>"+str+"</option>"; }
public final static String option (String attr, String str) { return "\n<option "+attr+">"+str+"</option>"; }
public final static String option (String[] attr) { return tag( "option", attr, null ); }
public final static String option (String[] attr, String str) { return tag( "option", attr, null )+str+"</option>"; }
public final static String end_option () { return "</option>"; }
  

public final static String p () { return "<p>"; }
public final static String p (String str) { return "\n<p>"+str+"</p>"; }
public final static String p (String attr, String str) { return "\n<p "+attr+">"+str+"</p>"; }
public final static String p (String[] attr) { return tag( "p", attr, null ); }
public final static String p (String[] attr, String str) { return tag( "p", attr, null )+str+"</p>"; }
public final static String end_p () { return "</p>"; }
  

public final static String pre () { return "<pre>"; }
public final static String pre (String str) { return "\n<pre>"+str+"</pre>"; }
public final static String pre (String attr, String str) { return "\n<pre "+attr+">"+str+"</pre>"; }
public final static String pre (String[] attr) { return tag( "pre", attr, null ); }
public final static String pre (String[] attr, String str) { return tag( "pre", attr, null )+str+"</pre>"; }
public final static String end_pre () { return "</pre>"; }
  

public final static String samp () { return "<samp>"; }
public final static String samp (String str) { return "\n<samp>"+str+"</samp>"; }
public final static String samp (String attr, String str) { return "\n<samp "+attr+">"+str+"</samp>"; }
public final static String samp (String[] attr) { return tag( "samp", attr, null ); }
public final static String samp (String[] attr, String str) { return tag( "samp", attr, null )+str+"</samp>"; }
public final static String end_samp () { return "</samp>"; }
  

public final static String script () { return "<script>"; }
public final static String script (String str) { return "\n<script>"+str+"</script>"; }
public final static String script (String attr, String str) { return "\n<script "+attr+">"+str+"</script>"; }
public final static String script (String[] attr) { return tag( "script", attr, null ); }
public final static String script (String[] attr, String str) { return tag( "script", attr, null )+str+"</script>"; }
public final static String end_script () { return "</script>"; }
  

public final static String small () { return "<small>"; }
public final static String small (String str) { return "\n<small>"+str+"</small>"; }
public final static String small (String attr, String str) { return "\n<small "+attr+">"+str+"</small>"; }
public final static String small (String[] attr) { return tag( "small", attr, null ); }
public final static String small (String[] attr, String str) { return tag( "small", attr, null )+str+"</small>"; }
public final static String end_small () { return "</small>"; }
  

public final static String span () { return "<span>"; }
public final static String span (String str) { return "\n<span>"+str+"</span>"; }
public final static String span (String attr, String str) { return "\n<span "+attr+">"+str+"</span>"; }
public final static String span (String[] attr) { return tag( "span", attr, null ); }
public final static String span (String[] attr, String str) { return tag( "span", attr, null )+str+"</span>"; }
public final static String end_span () { return "</span>"; }
  

public final static String start_form () { return "<form>"; }
public final static String start_form (String str) { return "\n<form>"+str+"</form>"; }
public final static String start_form (String attr, String str) { return "\n<form "+attr+">"+str+"</form>"; }
public final static String start_form (String[] attr) { return tag( "form", attr, null ); }
public final static String start_form (String[] attr, String str) { return tag( "form", attr, null )+str+"</form>"; }
public final static String end_start_form () { return "</start_form>"; }
  

public final static String strike () { return "<strike>"; }
public final static String strike (String str) { return "\n<strike>"+str+"</strike>"; }
public final static String strike (String attr, String str) { return "\n<strike "+attr+">"+str+"</strike>"; }
public final static String strike (String[] attr) { return tag( "strike", attr, null ); }
public final static String strike (String[] attr, String str) { return tag( "strike", attr, null )+str+"</strike>"; }
public final static String end_strike () { return "</strike>"; }
  

public final static String strong () { return "<strong>"; }
public final static String strong (String str) { return "\n<strong>"+str+"</strong>"; }
public final static String strong (String attr, String str) { return "\n<strong "+attr+">"+str+"</strong>"; }
public final static String strong (String[] attr) { return tag( "strong", attr, null ); }
public final static String strong (String[] attr, String str) { return tag( "strong", attr, null )+str+"</strong>"; }
public final static String end_strong () { return "</strong>"; }
  

public final static String style () { return "<style>"; }
public final static String style (String str) { return "\n<style>"+str+"</style>"; }
public final static String style (String attr, String str) { return "\n<style "+attr+">"+str+"</style>"; }
public final static String style (String[] attr) { return tag( "style", attr, null ); }
public final static String style (String[] attr, String str) { return tag( "style", attr, null )+str+"</style>"; }
public final static String end_style () { return "</style>"; }
  

public final static String sup () { return "<sup>"; }
public final static String sup (String str) { return "\n<sup>"+str+"</sup>"; }
public final static String sup (String attr, String str) { return "\n<sup "+attr+">"+str+"</sup>"; }
public final static String sup (String[] attr) { return tag( "sup", attr, null ); }
public final static String sup (String[] attr, String str) { return tag( "sup", attr, null )+str+"</sup>"; }
public final static String end_sup () { return "</sup>"; }
  

public final static String table () { return "<table>"; }
public final static String table (String str) { return "\n<table>"+str+"</table>"; }
public final static String table (String attr, String str) { return "\n<table "+attr+">"+str+"</table>"; }
public final static String table (String[] attr) { return tag( "table", attr, null ); }
public final static String table (String[] attr, String str) { return tag( "table", attr, null )+str+"</table>"; }
public final static String end_table () { return "</table>"; }
  

public final static String tr () { return "<tr>"; }
public final static String tr (String str) { return "\n<tr>"+str+"</tr>"; }
public final static String tr (String attr, String str) { return "\n<tr "+attr+">"+str+"</tr>"; }
public final static String tr (String[] attr) { return tag( "tr", attr, null ); }
public final static String tr (String[] attr, String str) { return tag( "tr", attr, null )+str+"</td>"; }
public final static String end_tr () { return "</tr>"; }
  

public final static String td () { return "<td>"; }
public final static String td (String str) { return "\n<td>"+str+"</td>"; }
public final static String td (String attr, String str) { return "\n<td "+attr+">"+str+"</td>"; }
public final static String td (String[] attr) { return tag( "td", attr, null ); }
public final static String td (String[] attr, String str) { return tag( "td", attr, null )+str+"</td>"; }
public final static String end_td () { return "</td>"; }
  

public final static String textarea () { return "<textarea>"; }
public final static String textarea (String str) { return "\n<textarea>"+str+"</textarea>"; }
public final static String textarea (String attr, String str) { return "\n<textarea "+attr+">"+str+"</textarea>"; }
public final static String textarea (String[] attr) { return tag( "textarea", attr, null ); }
public final static String textarea (String[] attr, String str) { return tag( "textarea", attr, null )+str+"</textarea>"; }
public final String end_textarea () { return "</textarea>"; }
  

public final static String th () { return "<th>"; }
public final static String th (String str) { return "\n<th>"+str+"</th>"; }
public final static String th (String attr, String str) { return "\n<th "+attr+">"+str+"</th>"; }
public final static String th (String[] attr) { return tag( "th", attr, null ); }
public final static String th (String[] attr, String str) { return tag( "th", attr, null )+str+"</th>"; }
public final static String end_th () { return "</th>"; }
  

public final static String thead () { return "<thead>"; }
public final static String thead (String str) { return "\n<thead>"+str+"</thead>"; }
public final static String thead (String attr, String str) { return "\n<thead "+attr+">"+str+"</thead>"; }
public final static String thead (String[] attr) { return tag( "thead", attr, null ); }
public final static String thead (String[] attr, String str) { return tag( "thead", attr, null )+str+"</thead>"; }
public final static String end_thead () { return "</thead>"; }
  

public final static String tbody () { return "<tbody>"; }
public final static String tbody (String str) { return "\n<tbody>"+str+"</tbody>"; }
public final static String tbody (String attr, String str) { return "\n<tbody "+attr+">"+str+"</tbody>"; }
public final static String tbody (String[] attr) { return tag( "tbody", attr, null ); }
public final static String tbody (String[] attr, String str) { return tag( "tbody", attr, null )+str+"</tbody>"; }
public final static String end_tbody () { return "</tbody>"; }


public final static String title () { return "<title>"; }
public final static String title (String str) { return "\n<title>"+str+"</title>"; }
public final static String title (String attr, String str) { return "\n<title "+attr+">"+str+"</title>"; }
public final static String title (String[] attr) { return tag( "title", attr, null ); }
public final static String title (String[] attr, String str) { return tag( "title", attr, null )+str+"</title>"; }
public final static String end_title () { return "</title>"; }


public final static String tt () { return "<tt>"; }
public final static String tt (String str) { return "\n<tt>"+str+"</tt>"; }
public final static String tt (String attr, String str) { return "\n<tt "+attr+">"+str+"</tt>"; }
public final static String tt (String[] attr) { return tag( "tt", attr, null ); }
public final static String tt (String[] attr, String str) { return tag( "tt", attr, null )+str+"</tt>"; }
public final static String end_tt () { return "</tt>"; }
  

public final static String u () { return "<u>"; }
public final static String u (String str) { return "\n<u>"+str+"</u>"; }
public final static String u (String attr, String str) { return "\n<u "+attr+">"+str+"</u>"; }
public final static String u (String[] attr) { return tag( "u", attr, null ); }
public final static String u (String[] attr, String str) { return tag( "u", attr, null )+str+"</u>"; }
public final static String end_u () { return "</u>"; }
  

public final static String ul () { return "<ul>"; }
public final static String ul (String str) { return "\n<ul>"+str+"</ul>"; }
public final static String ul (String attr, String str) { return "\n<ul "+attr+">"+str+"</ul>"; }
public final static String ul (String[] attr) { return tag( "ul", attr, null ); }
public final static String ul (String[] attr, String str) { return tag( "ul", attr, null )+str+"</ul>"; }
public final static String end_ul () { return "</ul>"; }
  

public final static String var () { return "<var>"; }
public final static String var (String str) { return "\n<var>"+str+"</var>"; }
public final static String var (String attr, String str) { return "\n<var "+attr+">"+str+"</var>"; }
public final static String var (String[] attr) { return tag( "var", attr, null ); }
public final static String var (String[] attr, String str) { return tag( "var", attr, null )+str+"</var>"; }
public final static String end_var () { return "</var>"; }


public final String textfield () { return inputs( "textfield", null, null ); }
public final String textfield ( String name ) { return inputs( "textfield", null, name ); }
public final String textfield ( String [] attr, String name ) { return inputs( "textfield", attr, name ); }
public final String password () { return inputs( "password", null, null ); }
public final String password ( String name ) { return inputs( "password", null, name ); }
public final String password ( String [] attr, String name ) { return inputs( "password", attr, name ); }
public final String hidden () { return inputs( "hidden", null, null ); }
public final String hidden ( String name ) { return inputs( "hidden", null, name ); }
public final String hidden ( String [] attr, String name ) { return inputs( "hidden", attr, name ); }
public final String submit () { return inputs( "submit", null, null ); }
public final String submit ( String name ) { return inputs( "submit", null, name ); }
public final String submit ( String [] attr, String name ) { return inputs( "submit", attr, name ); }
//OTUA
}

