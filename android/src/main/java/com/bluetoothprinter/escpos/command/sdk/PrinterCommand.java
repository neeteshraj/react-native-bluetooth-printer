package com.bluetoothprinter.escpos.command.sdk;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class PrinterCommand {

  /**

Printer initialization
@return
*/
  public static byte[] POS_Set_PrtInit() {
    return Command.ESC_Init;
  }

  /**

Print and line feed
@return
*/
  public static byte[] POS_Set_LF() {
    return Command.LF;
  }

  /**

Print and feed paper (0~255)
@param feed
@return
*/

  public static byte[] POS_Set_PrtAndFeedPaper(int feed) {
    if (feed > 255 | feed < 0) return null;

    byte[] data = Arrays.copyOf(Command.ESC_J, Command.ESC_J.length);
    data[2] = (byte) feed;
    return data;
  }

  /**

Print self-test page
@return
*/
  public static byte[] POS_Set_PrtSelfTest() {
    return Command.US_vt_eot;
  }

  /**

Buzzer command
@param m Number of beeps
@param t Duration of each beep
@return
*/
  public static byte[] POS_Set_Beep(int m, int t) {
    if ((m < 1 || m > 9) | (t < 1 || t > 9)) return null;

    byte[] data = Arrays.copyOf(Command.ESC_B_m_n, Command.ESC_B_m_n.length);
    data[2] = (byte) m;
    data[3] = (byte) t;
    return data;
  }

  /**

Cutter command (feed paper to cutter position and cut the paper)
@param cut 0~255
@return
*/
  public static byte[] POS_Set_Cut(int cut) {
    if (cut > 255 | cut < 0) return null;

    byte[] data = Arrays.copyOf(Command.GS_V_m_n, Command.GS_V_m_n.length);
    data[3] = (byte) cut;
    return data;
  }

  /**

Cash drawer command
@param nMode
@param nTime1
@param nTime2
@return
*/
  public static byte[] POS_Set_Cashbox(int nMode, int nTime1, int nTime2) {
    if (
      (nMode < 0 || nMode > 1) |
      nTime1 < 0 |
      nTime1 > 255 |
      nTime2 < 0 |
      nTime2 > 255
    ) return null;

    byte[] data = Arrays.copyOf(Command.ESC_p, Command.ESC_p.length);
    data[2] = (byte) nMode;
    data[3] = (byte) nTime1;
    data[4] = (byte) nTime2;
    return data;
  }

  /**

Set absolute print position
@param absolute
@return
*/
  public static byte[] POS_Set_Absolute(int absolute) {
    if (absolute > 65535 | absolute < 0) return null;

    byte[] data = Arrays.copyOf(
      Command.ESC_Absolute,
      Command.ESC_Absolute.length
    );
    data[2] = (byte) (absolute % 0x100);
    data[3] = (byte) (absolute / 0x100);
    return data;
  }

  /**

    Set relative print position
    @param relative
    @return
    */
  public static byte[] POS_Set_Relative(int relative) {
    if (relative < 0 | relative > 65535) return null;

    byte[] data = Arrays.copyOf(
      Command.ESC_Relative,
      Command.ESC_Relative.length
    );
    data[2] = (byte) (relative % 0x100);
    data[3] = (byte) (relative / 0x100);
    return data;
  }

  /**

Set left margin
@param left
@return
*/
  public static byte[] POS_Set_LeftSP(int left) {
    if (left > 255 | left < 0) return null;

    byte[] data = Arrays.copyOf(Command.GS_LeftSp, Command.GS_LeftSp.length);
    data[2] = (byte) (left % 0x100);
    data[3] = (byte) (left / 0x100);
    return data;
  }

  /**

Set alignment mode
@param align
@return
*/
  public static byte[] POS_S_Align(int align) {
    if ((align < 0 || align > 2) && (align < 48 || align > 50)) return null;

    byte[] data = Arrays.copyOf(Command.ESC_Align, Command.ESC_Align.length);
    data[2] = (byte) align;
    return data;
  }

  /**

Set print area width
@param width
@return
*/
  public static byte[] POS_Set_PrintWidth(int width) {
    if (width < 0 | width > 255) return null;

    byte[] data = Arrays.copyOf(Command.GS_W, Command.GS_W.length);
    data[2] = (byte) (width % 0x100);
    data[3] = (byte) (width / 0x100);
    return data;
  }

  /**

Set default line spacing
@return
*/
  public static byte[] POS_Set_DefLineSpace() {
    return Command.ESC_Two;
  }

  /**

Set line spacing
@param space
@return
*/
  public static byte[] POS_Set_LineSpace(int space) {
    if (space < 0 | space > 255) return null;

    byte[] data = Arrays.copyOf(Command.ESC_Three, Command.ESC_Three.length);
    data[2] = (byte) space;
    return data;
  }

  /**

Select character code page
@param page
@return
*/
  public static byte[] POS_Set_CodePage(int page) {
    if (page > 255) return null;

    byte[] data = Arrays.copyOf(Command.ESC_t, Command.ESC_t.length);
    data[2] = (byte) page;
    return data;
  }

  /**

Print text document
@param pszString String to be printed
@param encoding Encoding for the printed characters
@param codepage Set code page (0--255)
@param nWidthTimes Width multiplier (0--4)
@param nHeightTimes Height multiplier (0--4)
@param nFontType Font type (only valid for ASCII characters) (0, 1 48, 49)
*/
  public static byte[] POS_Print_Text(
    String pszString,
    String encoding,
    int codepage,
    int nWidthTimes,
    int nHeightTimes,
    int nFontType
  ) {
    if (
      codepage < 0 ||
      codepage > 255 ||
      pszString == null ||
      "".equals(pszString) ||
      pszString.length() < 1
    ) {
      return null;
    }

    byte[] pbString = null;
    try {
      pbString = pszString.getBytes(encoding);
    } catch (UnsupportedEncodingException e) {
      return null;
    }

    byte[] intToWidth = { 0x00, 0x10, 0x20, 0x30 };
    byte[] intToHeight = { 0x00, 0x01, 0x02, 0x03 };
    byte[] gsExclamationMark = Arrays.copyOf(
      Command.GS_ExclamationMark,
      Command.GS_ExclamationMark.length
    );
    gsExclamationMark[2] =
      (byte) (intToWidth[nWidthTimes] + intToHeight[nHeightTimes]);
    byte[] escT = Arrays.copyOf(Command.ESC_t, Command.ESC_t.length);
    escT[2] = (byte) codepage;
    byte[] escM = Arrays.copyOf(Command.ESC_M, Command.ESC_M.length);
    escM[2] = (byte) nFontType;
    byte[] data = null;
    data = concatAll(gsExclamationMark, escT, escM, pbString);

    return data;
  }

  /**

Bold command (the least significant bit is valid)
@param bold
@return
*/
  public static byte[] POS_Set_Bold(int bold) {
    byte[] escE = Arrays.copyOf(Command.ESC_E, Command.ESC_E.length);
    byte[] escG = Arrays.copyOf(Command.ESC_G, Command.ESC_G.length);

    escE[2] = (byte) bold;
    escG[2] = (byte) bold;
    return concatAll(escE, escG);
  }

  /**

    Set inverted printing mode (valid when the least significant bit is 1)
    @param brace
    @return
    */
  public static byte[] POS_Set_LeftBrace(int brace) {
    byte[] data = Arrays.copyOf(
      Command.ESC_LeftBrace,
      Command.ESC_LeftBrace.length
    );
    data[2] = (byte) brace;
    return data;
  }

  /**

Set underline
@param line
@return
*/
  public static byte[] POS_Set_UnderLine(int line) {
    if ((line < 0 || line > 2)) return null;
    byte[] escMins = Arrays.copyOf(Command.ESC_Minus, Command.ESC_Minus.length);
    escMins[2] = (byte) line;
    byte[] fsMinus = Arrays.copyOf(Command.FS_Minus, Command.FS_Minus.length);
    fsMinus[2] = (byte) line;
    return concatAll(escMins, fsMinus);
  }

  /**

Select font size (double height and width)
@param size1
@param size2
@return
*/
  public static byte[] POS_Set_FontSize(int size1, int size2) {
    if (size1 < 0 | size1 > 7 | size2 < 0 | size2 > 7) return null;
    byte[] intToWidth = { 0x00, 0x10, 0x20, 0x30, 0x40, 0x50, 0x60, 0x70 };
    byte[] intToHeight = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };
    byte[] data = Arrays.copyOf(
      Command.GS_ExclamationMark,
      Command.GS_ExclamationMark.length
    );
    data[2] = (byte) (intToWidth[size1] + intToHeight[size2]);
    return data;
  }

  /**

Set reverse printing
@param inverse
@return
*/
  public static byte[] POS_Set_Inverse(int inverse) {
    byte[] data = Arrays.copyOf(Command.GS_B, Command.GS_B.length);
    data[2] = (byte) inverse;
    return data;
  }

  /**

Set 90-degree rotation printing
@param rotate
@return
*/
  public static byte[] POS_Set_Rotate(int rotate) {
    if (rotate < 0 || rotate > 1) return null;

    byte[] data = Arrays.copyOf(Command.ESC_V, Command.ESC_V.length);
    data[2] = (byte) rotate;
    return data;
  }

  /**

    Select font type
    @param font
    @return
    */
  public static byte[] POS_Set_ChoseFont(int font) {
    if (font > 1 | font < 0) return null;

    byte[] data = Arrays.copyOf(Command.ESC_M, Command.ESC_M.length);
    data[2] = (byte) font;
    return data;
  }

  /**
   *
   *
   *
   * @return
   */
  public static byte[] POS_Cut_One_Point() {
    return Command.GS_i;
  }

  //*********************************** Public Functions Below ***********************************************************//

  /**

    Function for printing QR code
    @param str QR code data to be printed
    @param nVersion QR code type
    @param nErrorCorrectionLevel Error correction level
    @param nMagnification Magnification factor
    @return
    */
  public static byte[] getQRCodeCommand(
    String str,
    int nVersion,
    int nErrorCorrectionLevel,
    int nMagnification
  ) {
    if (
      nVersion < 0 |
      nVersion > 19 |
      nErrorCorrectionLevel < 0 |
      nErrorCorrectionLevel > 3 |
      nMagnification < 1 |
      nMagnification > 8
    ) {
      return null;
    }

    byte[] bCodeData = null;
    try {
      bCodeData = str.getBytes("GBK");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }

    byte[] command = new byte[bCodeData.length + 7];

    command[0] = 27;
    command[1] = 90;
    command[2] = ((byte) nVersion);
    command[3] = ((byte) nErrorCorrectionLevel);
    command[4] = ((byte) nMagnification);
    command[5] = (byte) (bCodeData.length & 0xff);
    command[6] = (byte) ((bCodeData.length & 0xff00) >> 8);
    System.arraycopy(bCodeData, 0, command, 7, bCodeData.length);

    return command;
  }

  /**

    Print one-dimensional barcode
    @param str Barcode characters to be printed
    @param nType Barcode type (65~73)
    @param nWidthX Barcode width
    @param nHeight Barcode height
    @param nHriFontType HRI font type
    @param nHriFontPosition HRI position
    @return
    */
  public static byte[] getBarCodeCommand(
    String str,
    int nType,
    int nWidthX,
    int nHeight,
    int nHriFontType,
    int nHriFontPosition
  ) {
    if (
      nType < 0x41 |
      nType > 0x49 |
      nWidthX < 2 |
      nWidthX > 6 |
      nHeight < 1 |
      nHeight > 255 |
      str.length() == 0
    ) return null;

    byte[] bCodeData = null;
    try {
      bCodeData = str.getBytes("GBK");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }

    byte[] command = new byte[bCodeData.length + 16];

    command[0] = 29;
    command[1] = 119;
    command[2] = ((byte) nWidthX);
    command[3] = 29;
    command[4] = 104;
    command[5] = ((byte) nHeight);
    command[6] = 29;
    command[7] = 102;
    command[8] = ((byte) (nHriFontType & 0x01));
    command[9] = 29;
    command[10] = 72;
    command[11] = ((byte) (nHriFontPosition & 0x03));
    command[12] = 29;
    command[13] = 107;
    command[14] = ((byte) nType);
    command[15] = (byte) (byte) bCodeData.length;
    System.arraycopy(bCodeData, 0, command, 16, bCodeData.length);

    return command;
  }

  /**

Set print mode (select font (A or B), bold, font size (up to 4 times height and width))
@param str String to be printed
@param bold Bold
@param font Select font (A or B)
@param widthsize Width multiplier
@param heigthsize Height multiplier
@return
*/
  public static byte[] POS_Set_Font(
    String str,
    int bold,
    int font,
    int widthsize,
    int heigthsize
  ) {
    if (
      str.length() == 0 |
      widthsize < 0 |
      widthsize > 4 |
      heigthsize < 0 |
      heigthsize > 4 |
      font < 0 |
      font > 1
    ) return null;

    byte[] strData = null;
    try {
      strData = str.getBytes("GBK");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }

    byte[] command = new byte[strData.length + 9];

    byte[] intToWidth = { 0x00, 0x10, 0x20, 0x30 }; // Up to four times width
    byte[] intToHeight = { 0x00, 0x01, 0x02, 0x03 }; // Up to four times height

    command[0] = 27;
    command[1] = 69;
    command[2] = ((byte) bold);
    command[3] = 27;
    command[4] = 77;
    command[5] = ((byte) font);
    command[6] = 29;
    command[7] = 33;
    command[8] = (byte) (intToWidth[widthsize] + intToHeight[heigthsize]);

    System.arraycopy(strData, 0, command, 9, strData.length);
    return command;
  }

  //**********************************************************************************************************//

  public static byte[] concatAll(byte[] first, byte[]... rest) {
    int totalLength = first.length;
    for (byte[] array : rest) {
      totalLength += array.length;
    }
    byte[] result = Arrays.copyOf(first, totalLength);
    int offset = first.length;
    for (byte[] array : rest) {
      System.arraycopy(array, 0, result, offset, array.length);
      offset += array.length;
    }
    return result;
  }
}
