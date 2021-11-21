package util;

import java.util.HashMap;
import java.util.Map;

import management.entities.monsters.pathfinding.DijkstraNode;

/**
 * Class that holds static methods to manipulate strings in the utf8 encodage,
 * and get letters from values in the menus.
 */
public abstract class UTF8StringUtility {

    /** returns the same string without its last char. */
    public static String removeLastChar(String toremove) {
	if (toremove.equals(""))
	    return "";
	return toremove.substring(0, toremove.length() - 1);
    }

    public static String getCharFrom(int x, int y, boolean uppercase) {
	if (y == 4)
	    switch (x) {
	    case 0:
		return "0";
	    case 1:
		return "1";
	    case 2:
		return "2";
	    case 3:
		return "3";
	    case 4:
		return "4";
	    case 5:
		return "5";
	    case 6:
		return "6";
	    case 7:
		return "7";
	    case 8:
		return "8";
	    case 9:
		return "9";
	    case 10:
		return ",";
	    case 11:
		return ".";
	    case 12:
		return " ";
	    }
	if (uppercase)
	    switch (y) {
	    case 0:
		switch (x) {
		case 0:
		    return "A";
		case 1:
		    return "B";
		case 2:
		    return "C";
		case 3:
		    return "D";
		case 4:
		    return "E";
		case 5:
		    return "F";
		case 6:
		    return "G";
		case 7:
		    return "H";
		case 8:
		    return "I";
		case 9:
		    return "J";
		case 10:
		    return "K";
		case 11:
		    return "L";
		case 12:
		    return "M";
		}
		break;
	    case 1:
		switch (x) {
		case 0:
		    return "N";
		case 1:
		    return "O";
		case 2:
		    return "P";
		case 3:
		    return "Q";
		case 4:
		    return "R";
		case 5:
		    return "S";
		case 6:
		    return "T";
		case 7:
		    return "U";
		case 8:
		    return "V";
		case 9:
		    return "W";
		case 10:
		    return "X";
		case 11:
		    return "Y";
		case 12:
		    return "Z";
		}
		break;
	    case 2:
		switch (x) {
		case 0:
		    return "�";
		case 1:
		    return "�";
		case 2:
		    return "�";
		case 3:
		    return "�";
		case 4:
		    return "�";
		case 5:
		    return "�";
		case 6:
		    return "�";
		case 7:
		    return "�";
		case 8:
		    return "�";
		case 9:
		    return "�";
		case 10:
		    return "�";
		case 11:
		    return "�";
		case 12:
		    return "�";
		}
		break;
	    case 3:
		switch (x) {
		case 0:
		    return "�";
		case 1:
		    return "�";
		case 2:
		    return "�";
		case 3:
		    return "�";
		case 4:
		    return "�";
		case 5:
		    return "�";
		case 6:
		    return "�";
		case 7:
		    return "�";
		case 8:
		    return "'";
		case 9:
		    return "-";
		case 10:
		    return ":";
		case 11:
		    return "!";
		case 12:
		    return "?";
		}
		break;
	    }
	else
	    switch (y) {
	    case 0:
		switch (x) {
		case 0:
		    return "a";
		case 1:
		    return "b";
		case 2:
		    return "c";
		case 3:
		    return "d";
		case 4:
		    return "e";
		case 5:
		    return "f";
		case 6:
		    return "g";
		case 7:
		    return "h";
		case 8:
		    return "i";
		case 9:
		    return "j";
		case 10:
		    return "k";
		case 11:
		    return "l";
		case 12:
		    return "m";
		}
		break;
	    case 1:
		switch (x) {
		case 0:
		    return "n";
		case 1:
		    return "o";
		case 2:
		    return "p";
		case 3:
		    return "q";
		case 4:
		    return "r";
		case 5:
		    return "s";
		case 6:
		    return "t";
		case 7:
		    return "u";
		case 8:
		    return "v";
		case 9:
		    return "w";
		case 10:
		    return "x";
		case 11:
		    return "y";
		case 12:
		    return "z";
		}
		break;
	    case 2:
		switch (x) {
		case 0:
		    return "�";
		case 1:
		    return "�";
		case 2:
		    return "�";
		case 3:
		    return "�";
		case 4:
		    return "�";
		case 5:
		    return "�";
		case 6:
		    return "�";
		case 7:
		    return "�";
		case 8:
		    return "�";
		case 9:
		    return "�";
		case 10:
		    return "�";
		case 11:
		    return "�";
		case 12:
		    return "�";
		}
		break;
	    case 3:
		switch (x) {
		case 0:
		    return "�";
		case 1:
		    return "�";
		case 2:
		    return "�";
		case 3:
		    return "�";
		case 4:
		    return "�";
		case 5:
		    return "�";
		case 6:
		    return "�";
		case 7:
		    return "�";
		default:
		    return " ";
		}
	    }
	return "*";
    }
    
}
