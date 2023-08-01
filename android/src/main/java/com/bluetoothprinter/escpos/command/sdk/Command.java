package com.bluetoothprinter.escpos.command.sdk;

public class Command {

    // ESC/POS Control Characters
    private static final byte ESC = 0x1B;  // Escape character, marks the start of an ESC/POS command.
    private static final byte FS = 0x1C;   // Field separator, used to indicate the start of a command parameter.
    private static final byte GS = 0x1D;   // Group separator, used to indicate the start of a command.
    private static final byte US = 0x1F;   // Unit separator, used for specific functions, e.g., self-test page.
    private static final byte DLE = 0x10;  // Data link escape, used for certain printer status and control commands.
    private static final byte DC4 = 0x14;  // Device control 4, used for controlling the cash drawer.
    private static final byte DC1 = 0x11;  // Device control 1, used for certain printer status commands.
    private static final byte SP = 0x20;   // Space character, used for character spacing settings.
    private static final byte NL = 0x0A;   // Newline, used for line feeds (moving to the next line).
    private static final byte FF = 0x0C;   // Form feed, used to advance the paper to the next page or clear the print buffer.
    public static final byte PIECE = (byte) 0xFF;  // A specific constant used in some commands.
    public static final byte NUL = (byte) 0x00;    // Null character, used as a terminator or filler.

    // Printer initialization
    public static byte[] ESC_Init = new byte[]{ESC, '@'};

    /**
     * Print commands
     */
    // Print and line feed
    public static byte[] LF = new byte[]{NL};

    // Print and feed paper
    public static byte[] ESC_J = new byte[]{ESC, 'J', 0x00};
    public static byte[] ESC_d = new byte[]{ESC, 'd', 0x00};

    // Print self-test page
    public static byte[] US_vt_eot = new byte[]{US, DC1, 0x04};

    // Buzzer command
    public static byte[] ESC_B_m_n = new byte[]{ESC, 'B', 0x00, 0x00};

    // Cutter command
    public static byte[] GS_V_n = new byte[]{GS, 'V', 0x00};
    public static byte[] GS_V_m_n = new byte[]{GS, 'V', 'B', 0x00};
    public static byte[] GS_i = new byte[]{ESC, 'i'};
    public static byte[] GS_m = new byte[]{ESC, 'm'};

    /**
     * Character setting commands
     */
    // Set character right spacing
    public static byte[] ESC_SP = new byte[]{ESC, SP, 0x00};

    // Set character printing font format
    public static byte[] ESC_ExclamationMark = new byte[]{ESC, '!', 0x00};

    // Set font size: double height and width
    public static byte[] GS_ExclamationMark = new byte[]{GS, '!', 0x00};

    // Set reverse printing
    public static byte[] GS_B = new byte[]{GS, 'B', 0x00};

    // Cancel/Select 90-degree rotation printing
    public static byte[] ESC_V = new byte[]{ESC, 'V', 0x00};

    // Select font type (mainly for ASCII characters)
    public static byte[] ESC_M = new byte[]{ESC, 'M', 0x00};

    // Select/Cancel bold instruction
    public static byte[] ESC_G = new byte[]{ESC, 'G', 0x00};
    public static byte[] ESC_E = new byte[]{ESC, 'E', 0x00};

    // Select/Cancel inverted printing mode
    public static byte[] ESC_LeftBrace = new byte[]{ESC, '{', 0x00};

    // Set underline dot height (for characters)
    public static byte[] ESC_Minus = new byte[]{ESC, 45, 0x00};

    // Character mode
    public static byte[] FS_dot = new byte[]{FS, 46};

    // Chinese character mode
    public static byte[] FS_and = new byte[]{FS, '&'};

    // Set Chinese character printing mode
    public static byte[] FS_ExclamationMark = new byte[]{FS, '!', 0x00};

    // Set underline dot height (for Chinese characters)
    public static byte[] FS_Minus = new byte[]{FS, 45, 0x00};

    // Set Chinese character left and right spacing
    public static byte[] FS_S = new byte[]{FS, 'S', 0x00, 0x00};

    // Select character code page
    public static byte[] ESC_t = new byte[]{ESC, 't', 0x00};

    /**
     * Format setting commands
     */
    // Set default line spacing
    public static byte[] ESC_Two = new byte[]{ESC, 50};

    // Set line spacing
    public static byte[] ESC_Three = new byte[]{ESC, 51, 0x00};

    // Set alignment mode
    public static byte[] ESC_Align = new byte[]{ESC, 'a', 0x00};

    // Set left margin
    public static byte[] GS_LeftSp = new byte[]{GS, 'L', 0x00, 0x00};

    // Set absolute print position
    // Set the current position to nL + nH x 256 characters from the beginning of the line.
    // If the set position is outside the specified print area, the command is ignored.
    public static byte[] ESC_Absolute = new byte[]{ESC, '$', 0x00, 0x00};

    // Set relative print position
    public static byte[] ESC_Relative = new byte[]{ESC, 92, 0x00, 0x00};

    // Set print area width
    public static byte[] GS_W = new byte[]{GS, 'W', 0x00, 0x00};

    /**
     * Status commands
     */
    // Real-time status transmission command
    public static byte[] DLE_eot = new byte[]{DLE, 0x04, 0x00};

    // Real-time cash drawer kick-out command
    public static byte[] DLE_DC4 = new byte[]{DLE, DC4, 0x00, 0x00, 0x00};

    // Standard cash drawer kick-out command
    public static byte[] ESC_p = new byte[]{ESC, 'p', 0x00, 0x00, 0x00};

    /**
     * Barcode setting commands
     */
    // Select HRI printing method
    public static byte[] GS_H = new byte[]{GS, 'H', 0x00};

    // Set barcode height
    public static byte[] GS_h = new byte[]{GS, 'h', (byte) 0xa2};

    // Set barcode width
    public static byte[] GS_w = new byte[]{GS, 'w', 0x00};

    // Set HRI character font type
    public static byte[] GS_f = new byte[]{GS, 'f', 0x00};

    // Barcode left offset command
    public static byte[] GS_x = new byte[]{GS, 'x', 0x00};

    // Print barcode command
    public static byte[] GS_k = new byte[]{GS, 'k', 'A', FF};

    // QR code-related commands
    public static byte[] GS_k_m_v_r_nL_nH = new byte[]{ESC, 'Z', 0x03, 0x03, 0x08, 0x00, 0x00};

}
