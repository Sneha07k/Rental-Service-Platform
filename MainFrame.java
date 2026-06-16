package findmyroomie;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

public class MainFrame extends JFrame {

    static final Color BG = new Color(245, 246, 248);
    static final Color SURFACE = new Color(255, 255, 255);
    static final Color SIDEBAR = new Color(18, 24, 38);
    static final Color ACCENT = new Color(15, 110, 86);
    static final Color ACCENT_L = new Color(225, 245, 238);
    static final Color ACCENT2 = new Color(29, 158, 117);
    static final Color TEXT = new Color(17, 24, 39);
    static final Color SUBTEXT = new Color(107, 114, 128);
    static final Color BORDER = new Color(229, 231, 235);
    static final Color DANGER = new Color(163, 45, 45);
    static final Color DANGER_L = new Color(252, 235, 235);
    static final Color WARNING = new Color(161, 100, 0);
    static final Color WARNING_L = new Color(254, 243, 199);
    static final Color INPUT_BG = new Color(249, 250, 251);

    static final Font F_TITLE = new Font("Segoe UI", Font.BOLD, 20);
    static final Font F_HEAD = new Font("Segoe UI", Font.BOLD, 15);
    static final Font F_LABEL = new Font("Segoe UI", Font.BOLD, 12);
    static final Font F_BODY = new Font("Segoe UI", Font.PLAIN, 13);
    static final Font F_SMALL = new Font("Segoe UI", Font.PLAIN, 11);
    static final Font F_MONO = new Font("Consolas", Font.PLAIN, 12);

    static final String SCREEN_LOGIN = "LOGIN";
    static final String SCREEN_REGISTER = "REGISTER";
    static final String SCREEN_HOST = "HOST";
    static final String SCREEN_SEEKER = "SEEKER";

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private User loggedInUser;

    public MainFrame() {
        setTitle("FindMyRoomie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1060, 700);
        setMinimumSize(new Dimension(860, 580));
        setLocationRelativeTo(null);
        setBackground(BG);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(BG);

        mainPanel.add(buildLoginScreen(), SCREEN_LOGIN);
        mainPanel.add(buildRegisterScreen(), SCREEN_REGISTER);

        add(mainPanel);
        cardLayout.show(mainPanel, SCREEN_LOGIN);
        setVisible(true);
    }

    static JButton btnPrimary(String text) {
        JButton b = new JButton(text);
        b.setBackground(ACCENT);
        b.setForeground(Color.WHITE);
        b.setFont(F_LABEL);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createEmptyBorder(9, 20, 9, 20));
        return b;
    }

    static JButton btnGhost(String text) {
        JButton b = new JButton(text);
        b.setBackground(SURFACE);
        b.setForeground(SUBTEXT);
        b.setFont(F_LABEL);
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(8, 18, 8, 18)));
        return b;
    }

    static JButton btnDanger(String text) {
        JButton b = new JButton(text);
        b.setBackground(DANGER_L);
        b.setForeground(DANGER);
        b.setFont(F_LABEL);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createEmptyBorder(7, 14, 7, 14));
        return b;
    }

    static JTextField field(String placeholder) {
        JTextField f = new JTextField(20);
        f.setBackground(INPUT_BG);
        f.setForeground(TEXT);
        f.setCaretColor(ACCENT);
        f.setFont(F_BODY);
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(8, 11, 8, 11)));
        return f;
    }

    static JPasswordField passField() {
        JPasswordField f = new JPasswordField(20);
        f.setBackground(INPUT_BG);
        f.setForeground(TEXT);
        f.setCaretColor(ACCENT);
        f.setFont(F_BODY);
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(8, 11, 8, 11)));
        return f;
    }

    static JComboBox<String> combo(String... items) {
        JComboBox<String> c = new JComboBox<>(items);
        c.setBackground(INPUT_BG);
        c.setForeground(TEXT);
        c.setFont(F_BODY);
        c.setBorder(BorderFactory.createLineBorder(BORDER));
        return c;
    }

    static JLabel lbl(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(SUBTEXT);
        l.setFont(F_LABEL);
        return l;
    }

    static JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(SURFACE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(16, 20, 16, 20)));
        return p;
    }

    static JScrollPane scroll(JComponent c) {
        JScrollPane sp = new JScrollPane(c);
        sp.setBackground(BG);
        sp.getViewport().setBackground(BG);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.getVerticalScrollBar().setUnitIncrement(16);
        return sp;
    }

    static void error(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    static void info(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }

    void switchToHost() {
        mainPanel.add(buildHostScreen(), SCREEN_HOST);
        cardLayout.show(mainPanel, SCREEN_HOST);
    }

    void switchToSeeker() {
        mainPanel.add(buildSeekerScreen(), SCREEN_SEEKER);
        cardLayout.show(mainPanel, SCREEN_SEEKER);
    }

    JPanel buildLoginScreen() {

        JPanel root = new JPanel(new GridLayout(1, 2));
        root.setBackground(BG);

        JPanel hero = new JPanel(new GridBagLayout());
        hero.setBackground(SIDEBAR);
        JPanel heroContent = new JPanel();
        heroContent.setLayout(new BoxLayout(heroContent, BoxLayout.Y_AXIS));
        heroContent.setBackground(SIDEBAR);
        heroContent.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        JLabel logo = new JLabel("FindMyRoomie");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(LEFT_ALIGNMENT);

        JLabel tagline = new JLabel("<html>Find your perfect room<br>or your ideal roommate.</html>");
        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tagline.setForeground(new Color(156, 175, 200));
        tagline.setAlignmentX(LEFT_ALIGNMENT);

        String[] feats = { "Verified listings with photos", "Compatibility-based matching",
                "Instant host notifications" };
        heroContent.add(logo);
        heroContent.add(Box.createVerticalStrut(14));
        heroContent.add(tagline);
        heroContent.add(Box.createVerticalStrut(36));
        for (String f : feats) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            row.setBackground(SIDEBAR);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
            JLabel dot = new JLabel("•  ");
            dot.setForeground(ACCENT2);
            dot.setFont(F_BODY);
            JLabel fl = new JLabel(f);
            fl.setFont(F_BODY);
            fl.setForeground(new Color(200, 210, 225));
            row.add(dot);
            row.add(fl);
            heroContent.add(row);
            heroContent.add(Box.createVerticalStrut(10));
        }

        hero.add(heroContent);
        root.add(hero);

        JPanel formWrap = new JPanel(new GridBagLayout());
        formWrap.setBackground(BG);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(BG);
        form.setPreferredSize(new Dimension(360, 420));

        JLabel heading = new JLabel("Welcome back");
        heading.setFont(F_TITLE);
        heading.setForeground(TEXT);
        heading.setAlignmentX(LEFT_ALIGNMENT);

        JLabel sub = new JLabel("Sign in to your account");
        sub.setFont(F_BODY);
        sub.setForeground(SUBTEXT);
        sub.setAlignmentX(LEFT_ALIGNMENT);

        JTextField emailF = field("Email");
        JPasswordField passF = passField();
        JButton loginBtn = btnPrimary("Sign in");
        JButton regBtn = btnGhost("Create an account");

        for (JComponent c : new JComponent[] { emailF, passF, loginBtn, regBtn }) {
            c.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
            c.setAlignmentX(LEFT_ALIGNMENT);
        }

        form.add(heading);
        form.add(Box.createVerticalStrut(6));
        form.add(sub);
        form.add(Box.createVerticalStrut(28));
        form.add(fieldBlock("Email address", emailF));
        form.add(Box.createVerticalStrut(14));
        form.add(fieldBlock("Password", passF));
        form.add(Box.createVerticalStrut(22));
        form.add(loginBtn);
        form.add(Box.createVerticalStrut(10));
        form.add(regBtn);

        loginBtn.addActionListener(e -> {
            String email = emailF.getText().trim();
            String pass = new String(passF.getPassword()).trim();
            if (email.isEmpty() || pass.isEmpty()) {
                error(this, "Please fill all fields.");
                return;
            }
            if (!Validator.isValidEmail(email)) {
                error(this, "Invalid email format.");
                return;
            }
            User u = UserDAO.login(email, pass);
            if (u == null) {
                error(this, "Wrong email or password.");
                return;
            }
            loggedInUser = u;
            if (u.getRole().equals("HOST"))
                switchToHost();
            else
                switchToSeeker();
        });
        regBtn.addActionListener(e -> showScreen(SCREEN_REGISTER));

        formWrap.add(form);
        root.add(formWrap);
        return root;
    }

    static JPanel fieldBlock(String labelText, JComponent field) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(BG);
        p.setAlignmentX(LEFT_ALIGNMENT);
        JLabel l = new JLabel(labelText);
        l.setFont(F_LABEL);
        l.setForeground(SUBTEXT);
        l.setAlignmentX(LEFT_ALIGNMENT);
        field.setAlignmentX(LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        p.add(l);
        p.add(Box.createVerticalStrut(5));
        p.add(field);
        return p;
    }

    JPanel buildRegisterScreen() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(SURFACE);
        topBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER),
                BorderFactory.createEmptyBorder(14, 24, 14, 24)));
        JLabel titleLbl = new JLabel("FindMyRoomie  —  Create account");
        titleLbl.setFont(F_TITLE);
        titleLbl.setForeground(ACCENT);
        topBar.add(titleLbl, BorderLayout.WEST);
        root.add(topBar, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(BG);
        form.setBorder(BorderFactory.createEmptyBorder(24, 80, 24, 80));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(5, 5, 5, 5);

        JTextField nameF = field("Full name");
        JTextField emailF = field("Email address");
        JPasswordField passF = passField();
        JTextField phoneF = field("10-digit mobile number");
        JTextField aadharF = field("12-digit Aadhaar (optional)");
        JTextField ageF = field("Age");
        JTextField cityF = field("Preferred city");
        JTextField occupF = field("Student / Working professional / etc.");
        JTextArea bioA = styledTextArea(3);

        JComboBox<String> roleC = combo("SEEKER", "HOST");
        JComboBox<String> genderC = combo("MALE", "FEMALE", "OTHER");
        JComboBox<String> smokingC = combo("NEVER", "OCCASIONALLY", "REGULARLY");
        JComboBox<String> drinkingC = combo("NEVER", "OCCASIONALLY", "REGULARLY");

        JLabel passHint = new JLabel(" ");
        passHint.setFont(F_SMALL);
        passHint.setForeground(SUBTEXT);
        passF.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String p = new String(passF.getPassword());
                String err = Validator.getPasswordError(p);
                if (err == null) {
                    passHint.setText("Strong password");
                    passHint.setForeground(ACCENT2);
                } else {
                    passHint.setText(err);
                    passHint.setForeground(DANGER);
                }
            }
        });

        int row = 0;
        Object[][] rows = {
                { "Full name", nameF },
                { "Email", emailF },
                { "Password", passF },
                { "", passHint },
                { "Phone", phoneF },
                { "Aadhaar number", aadharF },
                { "Age", ageF },
                { "Role", roleC },
                { "Gender", genderC },
                { "Smoking habit", smokingC },
                { "Drinking habit", drinkingC },
                { "Occupation", occupF },
                { "Preferred city", cityF },
                { "About you (bio)", new JScrollPane(bioA) },
        };

        for (Object[] r : rows) {
            String lbText = (String) r[0];
            Component comp = (Component) r[1];
            if (!lbText.isEmpty()) {
                g.gridx = 0;
                g.gridy = row;
                g.weightx = 0.28;
                JLabel lb = lbl(lbText);
                lb.setFont(F_LABEL);
                form.add(lb, g);
            }
            g.gridx = 1;
            g.gridy = row;
            g.weightx = 0.72;
            comp.setPreferredSize(new Dimension(320, 36));
            form.add(comp, g);
            row++;
        }

        JButton regBtn = btnPrimary("Create account");
        JButton backBtn = btnGhost("< Back to login");
        g.gridx = 1;
        g.gridy = row++;
        form.add(regBtn, g);
        g.gridx = 1;
        g.gridy = row;
        form.add(backBtn, g);

        regBtn.addActionListener(e -> {
            String name = nameF.getText().trim();
            String email = emailF.getText().trim();
            String pass = new String(passF.getPassword()).trim();
            String phone = phoneF.getText().trim();
            String aadhar = aadharF.getText().trim();
            String ageStr = ageF.getText().trim();
            String city = cityF.getText().trim();
            String occup = occupF.getText().trim();
            String bio = bioA.getText().trim();
            String role = (String) roleC.getSelectedItem();
            String gender = (String) genderC.getSelectedItem();
            String smoking = (String) smokingC.getSelectedItem();
            String drinking = (String) drinkingC.getSelectedItem();

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || phone.isEmpty()) {
                error(this, "Name, email, password and phone are required.");
                return;
            }
            if (!Validator.isValidEmail(email)) {
                error(this, "Invalid email format.");
                return;
            }
            if (!Validator.isValidPhone(phone)) {
                error(this, "Phone must be 10 digits starting with 6–9.");
                return;
            }
            if (!aadhar.isEmpty() && !Validator.isValidAadhar(aadhar)) {
                error(this, "Aadhaar must be 12 digits.");
                return;
            }
            String passErr = Validator.getPasswordError(pass);
            if (passErr != null) {
                error(this, passErr);
                return;
            }
            if (UserDAO.emailExists(email)) {
                error(this, "Email already registered.");
                return;
            }
            int age = 0;
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException ignored) {
            }

            boolean ok = UserDAO.register(name, email, pass, phone, role, aadhar,
                    smoking, drinking, occup, gender, city, age, bio);
            if (ok) {
                info(this, "Account created! Please sign in.");
                showScreen(SCREEN_LOGIN);
            } else
                error(this, "Registration failed:\n" +
                        (UserDAO.lastError.isEmpty() ? "Unknown error." : UserDAO.lastError));
        });
        backBtn.addActionListener(e -> showScreen(SCREEN_LOGIN));

        root.add(scroll(form), BorderLayout.CENTER);
        return root;
    }

    static JTextArea styledTextArea(int rows) {
        JTextArea a = new JTextArea(rows, 20);
        a.setBackground(INPUT_BG);
        a.setForeground(TEXT);
        a.setFont(F_BODY);
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(7, 10, 7, 10)));
        return a;
    }

    Object[] buildDashboardShell(String roleLabel, String[] tabs, String logoutLabel) {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(SIDEBAR);
        sidebar.setPreferredSize(new Dimension(210, 0));

        JPanel brandBlock = new JPanel();
        brandBlock.setLayout(new BoxLayout(brandBlock, BoxLayout.Y_AXIS));
        brandBlock.setBackground(SIDEBAR);
        brandBlock.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(40, 52, 72)),
                BorderFactory.createEmptyBorder(20, 20, 18, 20)));
        JLabel brandName = new JLabel("FindMyRoomie");
        brandName.setFont(new Font("Segoe UI", Font.BOLD, 15));
        brandName.setForeground(Color.WHITE);
        brandName.setAlignmentX(LEFT_ALIGNMENT);
        JLabel brandRole = new JLabel(roleLabel);
        brandRole.setFont(F_SMALL);
        brandRole.setForeground(new Color(100, 120, 150));
        brandRole.setAlignmentX(LEFT_ALIGNMENT);
        brandBlock.add(brandName);
        brandBlock.add(Box.createVerticalStrut(3));
        brandBlock.add(brandRole);
        sidebar.add(brandBlock);
        sidebar.add(Box.createVerticalStrut(12));

        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(BG);
        CardLayout cl = (CardLayout) contentPanel.getLayout();

        JButton[] navBtns = new JButton[tabs.length];
        for (int i = 0; i < tabs.length; i++) {
            final String tab = tabs[i];
            JButton b = new JButton("  " + tab);
            b.setFont(F_BODY);
            b.setForeground(new Color(160, 175, 200));
            b.setBackground(SIDEBAR);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setOpaque(true);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 3, 0, 0, SIDEBAR), // inactive = invisible left border
                    BorderFactory.createEmptyBorder(0, 17, 0, 12)));
            b.addActionListener(ev -> {
                cl.show(contentPanel, tab);

                for (JButton nb : navBtns) {
                    nb.setForeground(new Color(160, 175, 200));
                    nb.setBackground(SIDEBAR);
                    nb.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createMatteBorder(0, 3, 0, 0, SIDEBAR),
                            BorderFactory.createEmptyBorder(0, 17, 0, 12)));
                }
                b.setForeground(Color.WHITE);
                b.setBackground(new Color(30, 40, 58));
                b.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 3, 0, 0, ACCENT2),
                        BorderFactory.createEmptyBorder(0, 17, 0, 12)));
            });
            navBtns[i] = b;
            sidebar.add(b);
            sidebar.add(Box.createVerticalStrut(2));
        }

        if (navBtns.length > 0) {
            navBtns[0].setForeground(Color.WHITE);
            navBtns[0].setBackground(new Color(30, 40, 58));
            navBtns[0].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 3, 0, 0, ACCENT2),
                    BorderFactory.createEmptyBorder(0, 17, 0, 12)));
        }

        sidebar.add(Box.createVerticalGlue());
        JButton logoutBtn = new JButton("  Sign out");
        logoutBtn.setFont(F_BODY);
        logoutBtn.setForeground(new Color(248, 113, 113));
        logoutBtn.setBackground(SIDEBAR);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setOpaque(true);
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        logoutBtn.setHorizontalAlignment(SwingConstants.LEFT);
        logoutBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(40, 52, 72)),
                BorderFactory.createEmptyBorder(0, 20, 0, 12)));
        logoutBtn.addActionListener(e -> {
            loggedInUser = null;
            showScreen(SCREEN_LOGIN);
        });
        sidebar.add(logoutBtn);

        root.add(sidebar, BorderLayout.WEST);
        root.add(contentPanel, BorderLayout.CENTER);

        return new Object[] { root, contentPanel, cl, navBtns };
    }

    JPanel pageHeader(String title, String subtitle) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(SURFACE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER),
                BorderFactory.createEmptyBorder(16, 24, 16, 24)));
        JLabel t = new JLabel(title);
        t.setFont(F_TITLE);
        t.setForeground(TEXT);
        JLabel s = new JLabel(subtitle);
        s.setFont(F_BODY);
        s.setForeground(SUBTEXT);
        JPanel inner = new JPanel();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.setBackground(SURFACE);
        t.setAlignmentX(LEFT_ALIGNMENT);
        s.setAlignmentX(LEFT_ALIGNMENT);
        inner.add(t);
        inner.add(Box.createVerticalStrut(2));
        inner.add(s);
        p.add(inner, BorderLayout.WEST);
        return p;
    }

    JPanel buildHostScreen() {
        String[] tabs = { "My Listings", "List a Room", "Room Requests", "My Profile" };
        Object[] shell = buildDashboardShell(
                "HOST  ·  " + loggedInUser.getName(), tabs, "Sign out");
        JPanel root = (JPanel) shell[0];
        JPanel contentPanel = (JPanel) shell[1];
        CardLayout cl = (CardLayout) shell[2];

        contentPanel.add(buildHostListingsPanel(contentPanel), tabs[0]);
        contentPanel.add(buildListRoomPanel(contentPanel), tabs[1]);
        contentPanel.add(buildHostRequestsPanel(), tabs[2]);
        contentPanel.add(buildProfilePanel(contentPanel), tabs[3]);
        cl.show(contentPanel, tabs[0]);

        return root;
    }

    JPanel buildListRoomPanel(JPanel parent) {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.add(pageHeader("List a room", "Post a new listing for seekers to find"), BorderLayout.NORTH);

        JPanel scrollable = new JPanel(new GridBagLayout());
        scrollable.setBackground(BG);
        scrollable.setBorder(BorderFactory.createEmptyBorder(24, 32, 24, 32));

        JPanel form = card();
        form.setLayout(new GridBagLayout());
        form.setPreferredSize(new Dimension(620, 640));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(6, 8, 6, 8);

        JTextField titleF = field("e.g. Bright 2BHK near Indiranagar");
        JTextField cityF = field("City");
        JTextField rentF = field("Monthly rent in ₹");
        JTextField bedsF = field("Bedrooms");
        JTextField bathsF = field("Bathrooms");
        JTextField availF = field("YYYY-MM-DD");
        JTextField amenF = field("WiFi, AC, Geyser, Parking …");
        JTextArea descA = styledTextArea(3);
        JComboBox<String> typeC = combo("SINGLE", "SHARED", "ENTIRE_APARTMENT");

        JCheckBox furnCB = styledCheck("Furnished");
        JCheckBox petsCB = styledCheck("Pets allowed");
        JCheckBox smokingCB = styledCheck("Smoking allowed");

        JLabel imageLabel = new JLabel("No photo selected");
        imageLabel.setFont(F_SMALL);
        imageLabel.setForeground(SUBTEXT);
        JButton pickImgBtn = btnGhost("Upload room photo");
        final String[] imagePath = { "" };
        pickImgBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png"));
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                imagePath[0] = f.getAbsolutePath();
                imageLabel.setText("✓ " + f.getName());
                imageLabel.setForeground(ACCENT2);
            }
        });

        int row = 0;
        Object[][] rows = {
                { "Title", titleF },
                { "City", cityF },
                { "Rent (₹/month)", rentF },
                { "Room type", typeC },
                { "Bedrooms", bedsF },
                { "Bathrooms", bathsF },
                { "Available from", availF },
                { "Amenities", amenF },
                { "Description", new JScrollPane(descA) },
                { "Room photo", pickImgBtn },
                { "", imageLabel },
                { "Options", furnCB },
                { "", petsCB },
                { "", smokingCB },
        };

        for (Object[] r : rows) {
            if (!((String) r[0]).isEmpty()) {
                g.gridx = 0;
                g.gridy = row;
                g.weightx = 0.3;
                form.add(lbl((String) r[0]), g);
            }
            g.gridx = 1;
            g.gridy = row;
            g.weightx = 0.7;
            ((Component) r[1]).setPreferredSize(new Dimension(320, 36));
            form.add((Component) r[1], g);
            row++;
        }

        JButton submitBtn = btnPrimary("Post listing");
        g.gridx = 1;
        g.gridy = row;
        form.add(submitBtn, g);

        submitBtn.addActionListener(e -> {
            try {
                String title = titleF.getText().trim();
                String city = cityF.getText().trim();
                double rent = Double.parseDouble(rentF.getText().trim());
                int beds = Integer.parseInt(bedsF.getText().trim());
                int baths = Integer.parseInt(bathsF.getText().trim());
                String type = (String) typeC.getSelectedItem();
                String avail = availF.getText().trim();
                String amen = amenF.getText().trim();
                String desc = descA.getText().trim();
                if (title.isEmpty() || city.isEmpty()) {
                    error(this, "Title and city are required.");
                    return;
                }
                boolean ok = RoomDAO.addRoom(title, city, rent, furnCB.isSelected(),
                        loggedInUser.getId(), desc, imagePath[0],
                        beds, baths, type, petsCB.isSelected(), smokingCB.isSelected(), amen, avail);
                if (ok) {
                    info(this, "Room listed successfully!");
                    titleF.setText("");
                    cityF.setText("");
                    rentF.setText("");
                    descA.setText("");
                    imagePath[0] = "";
                    imageLabel.setText("No photo selected");
                    imageLabel.setForeground(SUBTEXT);
                } else
                    error(this, "Failed to list room.");
            } catch (NumberFormatException ex) {
                error(this, "Rent, bedrooms and bathrooms must be numbers.");
            }
        });

        GridBagConstraints wc = new GridBagConstraints();
        wc.gridx = 0;
        wc.gridy = 0;
        wc.weightx = 1;
        wc.weighty = 1;
        wc.fill = GridBagConstraints.BOTH;
        scrollable.add(scroll(form), wc);
        root.add(scrollable, BorderLayout.CENTER);
        return root;
    }

    static JCheckBox styledCheck(String text) {
        JCheckBox cb = new JCheckBox(text);
        cb.setBackground(SURFACE);
        cb.setForeground(TEXT);
        cb.setFont(F_BODY);
        return cb;
    }

    JPanel buildHostListingsPanel(JPanel parent) {
        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(BG);
        root.add(pageHeader("My listings", "Manage your posted rooms"), BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        JButton refreshBtn = btnGhost(" Refresh");
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        bottomBar.setBackground(BG);
        bottomBar.add(refreshBtn);
        refreshBtn.addActionListener(e -> loadHostListings(listPanel));

        root.add(scroll(listPanel), BorderLayout.CENTER);
        root.add(bottomBar, BorderLayout.SOUTH);
        loadHostListings(listPanel);
        return root;
    }

    void loadHostListings(JPanel listPanel) {
        listPanel.removeAll();
        List<Room> rooms = RoomDAO.getRoomsByHost(loggedInUser.getId());
        if (rooms.isEmpty()) {
            listPanel.add(emptyState("No listings yet", "Switch to \"List a Room\" to add your first room."));
        } else {
            for (Room r : rooms) {
                listPanel.add(buildRoomCard(r, true, listPanel));
                listPanel.add(Box.createVerticalStrut(12));
            }
        }
        listPanel.revalidate();
        listPanel.repaint();
    }

    JPanel buildRoomCard(Room r, boolean showDelete, JPanel listPanel) {

        JPanel card = new JPanel(new BorderLayout(14, 0));
        card.setBackground(SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        // IMAGE (left)
        if (r.getImagePath() != null && !r.getImagePath().isEmpty()) {
            try {
                ImageIcon raw = new ImageIcon(r.getImagePath());
                ImageIcon img = new ImageIcon(raw.getImage()
                        .getScaledInstance(120, 88, Image.SCALE_SMOOTH));

                JLabel imgLbl = new JLabel(img);
                imgLbl.setBorder(BorderFactory.createLineBorder(BORDER));
                card.add(imgLbl, BorderLayout.WEST);

            } catch (Exception ignored) {
            }
        } else {
            JPanel swatch = new JPanel(new BorderLayout());
            swatch.setBackground(ACCENT_L);
            swatch.setPreferredSize(new Dimension(120, 88));
            swatch.setBorder(BorderFactory.createLineBorder(BORDER));

            JLabel icon = new JLabel("Find My Roomie", SwingConstants.CENTER);
            icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));

            swatch.add(icon, BorderLayout.CENTER);
            card.add(swatch, BorderLayout.WEST);
        }

        // INFO (CENTER)
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(SURFACE);
        info.setAlignmentX(Component.LEFT_ALIGNMENT); // IMPORTANT FIX

        JLabel titleLbl = new JLabel(r.getTitle());
        titleLbl.setFont(F_HEAD);
        titleLbl.setForeground(TEXT);
        titleLbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel meta = new JLabel(
                r.getCity() + "  ·  " + r.getBedrooms() + " bed / " +
                        r.getBathrooms() + " bath  ·  " + r.getRoomType());
        meta.setFont(F_BODY);
        meta.setForeground(SUBTEXT);
        meta.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel tags = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        tags.setBackground(SURFACE);
        tags.setAlignmentX(Component.LEFT_ALIGNMENT); // IMPORTANT FIX

        if (r.isFurnished())
            tags.add(greenTag("Furnished"));
        if (r.isPetsAllowed())
            tags.add(greenTag("Pets ok"));
        else
            tags.add(redTag("No pets"));
        if (r.isSmokingAllowed())
            tags.add(greenTag("Smoking ok"));

        if (r.getAmenities() != null && !r.getAmenities().isEmpty()) {
            for (String a : r.getAmenities().split(",")) {
                String t = a.trim();
                if (!t.isEmpty())
                    tags.add(grayTag(t));
            }
        }

        tags.add(grayTag("From " + r.getAvailableFrom()));

        info.add(titleLbl);
        info.add(Box.createVerticalStrut(4));
        info.add(meta);
        info.add(Box.createVerticalStrut(6));
        info.add(tags);

        card.add(info, BorderLayout.CENTER);

        // RIGHT SIDE (price/actions)
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBackground(SURFACE);
        right.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel price = new JLabel("₹" + (int) r.getRent());
        price.setFont(new Font("Segoe UI", Font.BOLD, 18));
        price.setForeground(ACCENT);
        price.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel perMonth = new JLabel("/month");
        perMonth.setFont(F_SMALL);
        perMonth.setForeground(SUBTEXT);
        perMonth.setAlignmentX(Component.CENTER_ALIGNMENT);

        right.add(Box.createVerticalGlue());
        right.add(price);
        right.add(perMonth);
        right.add(Box.createVerticalStrut(10));

        if (showDelete) {
            JButton del = btnDanger("Delete");
            del.setAlignmentX(Component.CENTER_ALIGNMENT);

            del.addActionListener(e -> {
                int c = JOptionPane.showConfirmDialog(
                        this,
                        "Delete \"" + r.getTitle() + "\"?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);

                if (c == JOptionPane.YES_OPTION) {
                    RoomDAO.deleteRoom(r.getId());
                    loadHostListings(listPanel);
                }
            });

            right.add(del);
        }

        right.add(Box.createVerticalGlue());
        card.add(right, BorderLayout.EAST);

        return card;
    }
    static JLabel greenTag(String t) {
        return tag(t, new Color(220, 245, 232), new Color(21, 128, 61));
    }

    static JLabel redTag(String t) {
        return tag(t, DANGER_L, DANGER);
    }

    static JLabel grayTag(String t) {
        return tag(t, new Color(243, 244, 246), SUBTEXT);
    }

    static JLabel tag(String text, Color bg, Color fg) {
        JLabel l = new JLabel(text);
        l.setFont(F_SMALL);
        l.setForeground(fg);
        l.setBackground(bg);
        l.setOpaque(true);
        l.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bg.darker()),
                BorderFactory.createEmptyBorder(2, 7, 2, 7)));
        return l;
    }

    JPanel buildHostRequestsPanel() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.add(pageHeader("Room requests", "Respond to seeker applications"), BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        Runnable refresh = () -> {
            listPanel.removeAll();
            List<Application> apps = ApplicationDAO.getByHost(loggedInUser.getId());
            if (apps.isEmpty()) {
                listPanel.add(emptyState("No requests yet", "When seekers apply to your rooms, they appear here."));
            } else {
                for (Application a : apps) {
                    listPanel.add(buildRequestCard(a, listPanel, true));
                    listPanel.add(Box.createVerticalStrut(12));
                }
            }
            listPanel.revalidate();
            listPanel.repaint();
        };

        refresh.run();
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        bottomBar.setBackground(BG);
        JButton refreshBtn = btnGhost(" Refresh");
        refreshBtn.addActionListener(e -> refresh.run());
        bottomBar.add(refreshBtn);

        root.add(scroll(listPanel), BorderLayout.CENTER);
        root.add(bottomBar, BorderLayout.SOUTH);
        return root;
    }

    JPanel buildRequestCard(Application a, JPanel listPanel, boolean showActions) {
        JPanel card = new JPanel(new BorderLayout(14, 0));
        card.setBackground(SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 175));

        Room r = RoomDAO.getRoomById(a.getRoomId());
        User sk = UserDAO.getUserById(a.getSeekerId());

        Color statusBg = a.getStatus().equals("ACCEPTED") ? new Color(220, 245, 232)
                : a.getStatus().equals("REJECTED") ? DANGER_L : WARNING_L;
        Color statusFg = a.getStatus().equals("ACCEPTED") ? new Color(21, 128, 61)
                : a.getStatus().equals("REJECTED") ? DANGER : WARNING;
        JLabel statusBadge = tag("● " + a.getStatus(), statusBg, statusFg);

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(SURFACE);

        JLabel roomLbl = new JLabel("Room: " + (r != null ? r.getTitle() : "#" + a.getRoomId()));
        roomLbl.setFont(F_HEAD);
        roomLbl.setForeground(TEXT);

        JLabel seekerLbl = new JLabel(
                (sk != null ? sk.getName() : "Unknown")
                        + "  ·  " + (sk != null ? sk.getPhone() : "?")
                        + "  ·  " + (sk != null ? sk.getOccupation() : ""));
        seekerLbl.setFont(F_BODY);
        seekerLbl.setForeground(SUBTEXT);

        JLabel habitsLbl = new JLabel(
                "Smoking: " + (sk != null ? sk.getSmokingHabit() : "?")
                        + "  ·  Drinking: " + (sk != null ? sk.getDrinkingHabit() : "?"));
        habitsLbl.setFont(F_SMALL);
        habitsLbl.setForeground(SUBTEXT);

        JLabel msgLbl = new JLabel("\"" + a.getMessage() + "\"");
        msgLbl.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        msgLbl.setForeground(SUBTEXT);

        JLabel moveLbl = new JLabel(
                "Move-in: " + a.getMoveInDate() + "  ·  Duration: " + a.getDurationMonths() + " months");
        moveLbl.setFont(F_SMALL);
        moveLbl.setForeground(SUBTEXT);

        info.add(statusBadge);
        info.add(Box.createVerticalStrut(5));
        info.add(roomLbl);
        info.add(Box.createVerticalStrut(3));
        info.add(seekerLbl);
        info.add(Box.createVerticalStrut(2));
        info.add(habitsLbl);
        info.add(Box.createVerticalStrut(4));
        info.add(msgLbl);
        info.add(Box.createVerticalStrut(2));
        info.add(moveLbl);
        card.add(info, BorderLayout.CENTER);

        if (showActions && a.getStatus().equals("PENDING")) {
            JPanel btnPanel = new JPanel(new GridLayout(2, 1, 6, 6));
            btnPanel.setBackground(SURFACE);
            JButton acc = btnPrimary("Accept");
            JButton rej = btnDanger("Reject");
            acc.addActionListener(e -> {
                ApplicationDAO.updateStatus(a.getId(), "ACCEPTED");
                buildHostRequestsPanel();
            });
            rej.addActionListener(e -> ApplicationDAO.updateStatus(a.getId(), "REJECTED"));
            btnPanel.add(acc);
            btnPanel.add(rej);
            card.add(btnPanel, BorderLayout.EAST);
        }
        return card;
    }

    JPanel buildSeekerScreen() {
        String[] tabs = { "Browse Rooms", "Find Roommate", "My Applications", "My Profile" };
        Object[] shell = buildDashboardShell(
                "SEEKER  ·  " + loggedInUser.getName(), tabs, "Sign out");
        JPanel root = (JPanel) shell[0];
        JPanel contentPanel = (JPanel) shell[1];
        CardLayout cl = (CardLayout) shell[2];

        contentPanel.add(buildBrowseRoomsPanel(), tabs[0]);
        contentPanel.add(buildRoommatePanel(), tabs[1]);
        contentPanel.add(buildMyApplicationsPanel(), tabs[2]);
        contentPanel.add(buildProfilePanel(contentPanel), tabs[3]);
        cl.show(contentPanel, tabs[0]);

        return root;
    }

    JPanel buildBrowseRoomsPanel() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.add(pageHeader("Browse rooms", "Search available rooms across cities"), BorderLayout.NORTH);

        JPanel filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        filterBar.setBackground(SURFACE);
        filterBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
        JTextField cityF = field("City");
        cityF.setPreferredSize(new Dimension(170, 36));
        JTextField maxRentF = field("Max rent (₹)");
        maxRentF.setPreferredSize(new Dimension(150, 36));
        JButton searchBtn = btnPrimary("Search");
        filterBar.add(lbl("City"));
        filterBar.add(cityF);
        filterBar.add(lbl("Max rent"));
        filterBar.add(maxRentF);
        filterBar.add(searchBtn);
        root.add(filterBar, BorderLayout.NORTH);

        JPanel northStack = new JPanel(new BorderLayout());
        northStack.setBackground(BG);
        northStack.add(pageHeader("Browse rooms", "Search available rooms across cities"), BorderLayout.NORTH);
        northStack.add(filterBar, BorderLayout.CENTER);
        root.add(northStack, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        Runnable loadRooms = () -> {
            listPanel.removeAll();
            String city = cityF.getText().trim();
            double maxRent = 0;
            try {
                maxRent = Double.parseDouble(maxRentF.getText().trim());
            } catch (Exception ignored) {
            }
            List<Room> rooms = RoomDAO.searchRooms(city, maxRent);
            if (rooms.isEmpty()) {
                listPanel.add(emptyState("No rooms found", "Try a different city or raise the rent limit."));
            } else {
                for (Room r : rooms) {
                    listPanel.add(buildSeekerRoomCard(r));
                    listPanel.add(Box.createVerticalStrut(12));
                }
            }
            listPanel.revalidate();
            listPanel.repaint();
        };

        searchBtn.addActionListener(e -> loadRooms.run());
        loadRooms.run();
        root.add(scroll(listPanel), BorderLayout.CENTER);
        return root;
    }

    JPanel buildSeekerRoomCard(Room r) {
        JPanel card = new JPanel(new BorderLayout(14, 0));
        card.setBackground(SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));

        if (r.getImagePath() != null && !r.getImagePath().isEmpty()) {
            try {
                ImageIcon raw = new ImageIcon(r.getImagePath());
                ImageIcon img = new ImageIcon(raw.getImage().getScaledInstance(130, 96, Image.SCALE_SMOOTH));
                JLabel imgLbl = new JLabel(img);
                imgLbl.setBorder(BorderFactory.createLineBorder(BORDER));
                card.add(imgLbl, BorderLayout.WEST);
            } catch (Exception ignored) {
            }
        } else {
            JPanel swatch = new JPanel(new BorderLayout());
            swatch.setBackground(ACCENT_L);
            swatch.setPreferredSize(new Dimension(130, 96));
            swatch.setBorder(BorderFactory.createLineBorder(BORDER));
            JLabel icon = new JLabel("Find My Roomie");
            icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 30));
            icon.setHorizontalAlignment(SwingConstants.CENTER);
            swatch.add(icon, BorderLayout.CENTER);
            card.add(swatch, BorderLayout.WEST);
        }

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(SURFACE);

        JLabel titleLbl = new JLabel(r.getTitle());
        titleLbl.setFont(F_HEAD);
        titleLbl.setForeground(TEXT);

        JLabel meta = new JLabel(
                r.getCity() + "  ·  " + r.getBedrooms() + " bed / " + r.getBathrooms()
                        + " bath  ·  " + r.getRoomType() + "  ·  From " + r.getAvailableFrom());
        meta.setFont(F_BODY);
        meta.setForeground(SUBTEXT);

        JPanel tags = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        tags.setBackground(SURFACE);
        if (r.isFurnished())
            tags.add(greenTag("Furnished"));
        if (r.isPetsAllowed())
            tags.add(greenTag("Pets ok"));
        else
            tags.add(redTag("No pets"));
        if (r.isSmokingAllowed())
            tags.add(greenTag("Smoking ok"));
        if (r.getAmenities() != null) {
            for (String a : r.getAmenities().split(",")) {
                String t = a.trim();
                if (!t.isEmpty())
                    tags.add(grayTag(t));
            }
        }

        JLabel descLbl = new JLabel("<html><body style='width:380px'>" + r.getDescription() + "</body></html>");
        descLbl.setFont(F_SMALL);
        descLbl.setForeground(SUBTEXT);

        info.add(titleLbl);
        info.add(Box.createVerticalStrut(4));
        info.add(meta);
        info.add(Box.createVerticalStrut(5));
        info.add(tags);
        info.add(Box.createVerticalStrut(5));
        info.add(descLbl);
        card.add(info, BorderLayout.CENTER);

        // Price + Apply
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBackground(SURFACE);
        JLabel price = new JLabel("₹" + (int) r.getRent());
        price.setFont(new Font("Segoe UI", Font.BOLD, 18));
        price.setForeground(ACCENT);
        price.setAlignmentX(CENTER_ALIGNMENT);
        JLabel perMonth = new JLabel("/month");
        perMonth.setFont(F_SMALL);
        perMonth.setForeground(SUBTEXT);
        perMonth.setAlignmentX(CENTER_ALIGNMENT);
        JButton applyBtn = btnPrimary("Apply");
        applyBtn.setAlignmentX(CENTER_ALIGNMENT);
        applyBtn.addActionListener(e -> showApplyDialog(r));

        right.add(Box.createVerticalGlue());
        right.add(price);
        right.add(perMonth);
        right.add(Box.createVerticalStrut(10));
        right.add(applyBtn);
        right.add(Box.createVerticalGlue());
        card.add(right, BorderLayout.EAST);
        return card;
    }

    void showApplyDialog(Room r) {
        JDialog dlg = new JDialog(this, "Apply — " + r.getTitle(), true);
        dlg.setSize(440, 300);
        dlg.setLocationRelativeTo(this);
        dlg.getContentPane().setBackground(SURFACE);
        dlg.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(8, 16, 8, 16);

        JTextField moveInF = field("YYYY-MM-DD");
        JTextField durF = field("e.g. 6");
        JTextArea msgA = styledTextArea(3);

        g.gridy = 0;
        g.gridx = 0;
        dlg.add(lbl("Move-in date"), g);
        g.gridx = 1;
        dlg.add(moveInF, g);
        g.gridy = 1;
        g.gridx = 0;
        dlg.add(lbl("Duration (months)"), g);
        g.gridx = 1;
        dlg.add(durF, g);
        g.gridy = 2;
        g.gridx = 0;
        dlg.add(lbl("Message to host"), g);
        g.gridx = 1;
        dlg.add(new JScrollPane(msgA), g);

        JButton submitBtn = btnPrimary("Submit application");
        g.gridy = 3;
        g.gridx = 1;
        dlg.add(submitBtn, g);

        submitBtn.addActionListener(ev -> {
            String moveIn = moveInF.getText().trim();
            String msg = msgA.getText().trim();
            int dur = 0;
            try {
                dur = Integer.parseInt(durF.getText().trim());
            } catch (Exception ignored) {
            }
            if (msg.isEmpty()) {
                error(dlg, "Please write a message to the host.");
                return;
            }
            boolean ok = ApplicationDAO.apply(loggedInUser.getId(), r.getId(), msg, moveIn, dur);
            if (ok) {
                info(dlg, "Application submitted!");
                dlg.dispose();
            } else
                error(dlg, "Failed to submit application.");
        });
        dlg.setVisible(true);
    }

    JPanel buildRoommatePanel() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.add(pageHeader("Find a roommate", "Sorted by compatibility with your profile"), BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        List<User> seekers = UserDAO.getSeekersSortedByCompatibility(loggedInUser);
        if (seekers.isEmpty()) {
            listPanel.add(emptyState("No other seekers found", "Check back later as more people join."));
        } else {
            for (User u : seekers) {
                listPanel.add(buildSeekerCard(u));
                listPanel.add(Box.createVerticalStrut(12));
            }
        }
        root.add(scroll(listPanel), BorderLayout.CENTER);
        return root;
    }

    JPanel buildSeekerCard(User u) {
        JPanel card = new JPanel(new BorderLayout(14, 0));
        card.setBackground(SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

        String initials = u.getName().length() >= 2
                ? ("" + u.getName().charAt(0) + u.getName().split(" ")[u.getName().split(" ").length - 1].charAt(0))
                        .toUpperCase()
                : u.getName().substring(0, 1).toUpperCase();
        JLabel avatar = new JLabel(initials, SwingConstants.CENTER);
        avatar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        avatar.setForeground(ACCENT);
        avatar.setBackground(ACCENT_L);
        avatar.setOpaque(true);
        avatar.setPreferredSize(new Dimension(48, 48));
        avatar.setBorder(BorderFactory.createEmptyBorder());

        JPanel avatarWrap = new JPanel(new GridBagLayout());
        avatarWrap.setBackground(SURFACE);
        avatarWrap.setPreferredSize(new Dimension(56, 48));
        avatarWrap.add(avatar);
        card.add(avatarWrap, BorderLayout.WEST);

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(SURFACE);

        int score = loggedInUser.compatibilityScore(u);

        JLabel nameLbl = new JLabel(
                u.getName() + "  (" + u.getGender() + (u.getAge() > 0 ? ", " + u.getAge() + " yrs" : "") + ")");
        nameLbl.setFont(F_HEAD);
        nameLbl.setForeground(TEXT);

        JLabel contact = new JLabel(u.getEmail() + "  ·  " + u.getPhone());
        contact.setFont(F_BODY);
        contact.setForeground(SUBTEXT);

        JLabel habits = new JLabel(
                "Smoking: " + u.getSmokingHabit()
                        + "  ·  Drinking: " + u.getDrinkingHabit()
                        + "  ·  " + u.getOccupation()
                        + "  ·  Prefers " + u.getPreferredCity());
        habits.setFont(F_SMALL);
        habits.setForeground(SUBTEXT);

        String bioText = (u.getBio() != null && !u.getBio().isEmpty()) ? u.getBio() : "No bio provided.";
        JLabel bioLbl = new JLabel("<html><i>" + bioText + "</i></html>");
        bioLbl.setFont(F_SMALL);
        bioLbl.setForeground(SUBTEXT);

        info.add(nameLbl);
        info.add(Box.createVerticalStrut(3));
        info.add(contact);
        info.add(Box.createVerticalStrut(2));
        info.add(habits);
        info.add(Box.createVerticalStrut(2));
        info.add(bioLbl);
        card.add(info, BorderLayout.CENTER);

        Color scoreColor = score >= 60 ? ACCENT2 : score >= 30 ? new Color(161, 100, 0) : DANGER;
        JPanel scorePnl = new JPanel();
        scorePnl.setLayout(new BoxLayout(scorePnl, BoxLayout.Y_AXIS));
        scorePnl.setBackground(SURFACE);
        JLabel scoreLbl = new JLabel(score + "%");
        scoreLbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
        scoreLbl.setForeground(scoreColor);
        scoreLbl.setAlignmentX(CENTER_ALIGNMENT);
        JLabel matchLbl = new JLabel("match");
        matchLbl.setFont(F_SMALL);
        matchLbl.setForeground(SUBTEXT);
        matchLbl.setAlignmentX(CENTER_ALIGNMENT);
        scorePnl.add(Box.createVerticalGlue());
        scorePnl.add(scoreLbl);
        scorePnl.add(matchLbl);
        scorePnl.add(Box.createVerticalGlue());
        card.add(scorePnl, BorderLayout.EAST);
        return card;
    }

    JPanel buildMyApplicationsPanel() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.add(pageHeader("My applications", "Track your submitted applications"), BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG);
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        List<Application> apps = ApplicationDAO.getBySeeker(loggedInUser.getId());
        if (apps.isEmpty()) {
            listPanel.add(emptyState("No applications yet", "Browse rooms and hit Apply to get started."));
        } else {
            for (Application a : apps) {
                listPanel.add(buildRequestCard(a, null, false));
                listPanel.add(Box.createVerticalStrut(12));
            }
        }
        root.add(scroll(listPanel), BorderLayout.CENTER);
        return root;
    }

    JPanel buildProfilePanel(JPanel parent) {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.add(pageHeader("My profile", "Update your personal details and preferences"), BorderLayout.NORTH);

        JPanel scrollable = new JPanel(new GridBagLayout());
        scrollable.setBackground(BG);
        scrollable.setBorder(BorderFactory.createEmptyBorder(24, 32, 24, 32));

        JPanel form = card();
        form.setLayout(new GridBagLayout());
        form.setPreferredSize(new Dimension(580, 560));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(6, 8, 6, 8);

        JTextField nameF = field("");
        nameF.setText(loggedInUser.getName());
        JTextField phoneF = field("");
        phoneF.setText(loggedInUser.getPhone());
        JTextField cityF = field("");
        cityF.setText(loggedInUser.getPreferredCity() != null ? loggedInUser.getPreferredCity() : "");
        JTextField occupF = field("");
        occupF.setText(loggedInUser.getOccupation() != null ? loggedInUser.getOccupation() : "");
        JTextField ageF = field("");
        ageF.setText(String.valueOf(loggedInUser.getAge()));
        JPasswordField passF = passField();
        JTextArea bioA = styledTextArea(3);
        bioA.setText(loggedInUser.getBio() != null ? loggedInUser.getBio() : "");

        JComboBox<String> smokingC = combo("NEVER", "OCCASIONALLY", "REGULARLY");
        JComboBox<String> drinkingC = combo("NEVER", "OCCASIONALLY", "REGULARLY");
        smokingC.setSelectedItem(loggedInUser.getSmokingHabit());
        drinkingC.setSelectedItem(loggedInUser.getDrinkingHabit());

        JLabel emailLbl = new JLabel(loggedInUser.getEmail());
        emailLbl.setFont(F_BODY);
        emailLbl.setForeground(TEXT);
        JLabel roleLbl = new JLabel(loggedInUser.getRole());
        roleLbl.setFont(F_LABEL);
        roleLbl.setForeground(ACCENT);

        JLabel passHint = new JLabel("Leave blank to keep current password");
        passHint.setFont(F_SMALL);
        passHint.setForeground(SUBTEXT);
        passF.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String p = new String(passF.getPassword());
                if (p.isEmpty()) {
                    passHint.setText("Leave blank to keep current password");
                    passHint.setForeground(SUBTEXT);
                    return;
                }
                String err = Validator.getPasswordError(p);
                if (err == null) {
                    passHint.setText("✓  Strong password");
                    passHint.setForeground(ACCENT2);
                } else {
                    passHint.setText("✗  " + err);
                    passHint.setForeground(DANGER);
                }
            }
        });

        int row = 0;
        Object[][] rows = {
                { "Email (read-only)", emailLbl }, { "Role", roleLbl },
                { "Name", nameF }, { "Phone", phoneF },
                { "New password", passF }, { "", passHint },
                { "Smoking habit", smokingC }, { "Drinking habit", drinkingC },
                { "Occupation", occupF }, { "Preferred city", cityF },
                { "Age", ageF }, { "Bio", new JScrollPane(bioA) },
        };

        for (Object[] r : rows) {
            if (!((String) r[0]).isEmpty()) {
                g.gridx = 0;
                g.gridy = row;
                g.weightx = 0.35;
                form.add(lbl((String) r[0]), g);
            }
            g.gridx = 1;
            g.gridy = row;
            g.weightx = 0.65;
            ((Component) r[1]).setPreferredSize(new Dimension(290, 36));
            form.add((Component) r[1], g);
            row++;
        }

        JButton saveBtn = btnPrimary("Save changes");
        g.gridx = 1;
        g.gridy = row;
        form.add(saveBtn, g);

        saveBtn.addActionListener(e -> {
            String name = nameF.getText().trim();
            String phone = phoneF.getText().trim();
            String pass = new String(passF.getPassword()).trim();
            String city = cityF.getText().trim();
            String occup = occupF.getText().trim();
            String bio = bioA.getText().trim();
            String smoke = (String) smokingC.getSelectedItem();
            String drink = (String) drinkingC.getSelectedItem();
            int age = 0;
            try {
                age = Integer.parseInt(ageF.getText().trim());
            } catch (Exception ignored) {
            }

            if (name.isEmpty()) {
                error(this, "Name cannot be empty.");
                return;
            }
            if (!phone.isEmpty() && !Validator.isValidPhone(phone)) {
                error(this, "Invalid phone number.");
                return;
            }
            if (!pass.isEmpty()) {
                String passErr = Validator.getPasswordError(pass);
                if (passErr != null) {
                    error(this, passErr);
                    return;
                }
            } else {
                pass = loggedInUser.getPassword();
            }
            boolean ok = UserDAO.updateProfile(loggedInUser.getId(), name, phone, pass,
                    smoke, drink, occup, city, age, bio);
            if (ok) {
                loggedInUser.setName(name);
                loggedInUser.setPhone(phone);
                loggedInUser.setPassword(pass);
                loggedInUser.setSmokingHabit(smoke);
                loggedInUser.setDrinkingHabit(drink);
                loggedInUser.setOccupation(occup);
                loggedInUser.setPreferredCity(city);
                loggedInUser.setAge(age);
                loggedInUser.setBio(bio);
                info(this, "Profile updated!");
            } else
                error(this, "Update failed.");
        });

        GridBagConstraints wc = new GridBagConstraints();
        wc.gridx = 0;
        wc.gridy = 0;
        wc.weightx = 1;
        wc.weighty = 1;
        wc.fill = GridBagConstraints.BOTH;
        scrollable.add(scroll(form), wc);
        root.add(scrollable, BorderLayout.CENTER);
        return root;
    }

    static JPanel emptyState(String heading, String sub) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(SURFACE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(32, 32, 32, 32)));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        JLabel h = new JLabel(heading);
        h.setFont(F_HEAD);
        h.setForeground(TEXT);
        h.setAlignmentX(CENTER_ALIGNMENT);
        JLabel s = new JLabel(sub);
        s.setFont(F_BODY);
        s.setForeground(SUBTEXT);
        s.setAlignmentX(CENTER_ALIGNMENT);
        p.add(h);
        p.add(Box.createVerticalStrut(6));
        p.add(s);
        return p;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        UIManager.put("Panel.background", new ColorUIResource(BG));
        UIManager.put("OptionPane.background", new ColorUIResource(SURFACE));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(TEXT));
        SwingUtilities.invokeLater(MainFrame::new);
    }

    static class ColorUIResource extends Color implements javax.swing.plaf.UIResource {
        public ColorUIResource(Color c) {
            super(c.getRGB());
        }

        public ColorUIResource(int r, int g, int b) {
            super(r, g, b);
        }
    }
}
