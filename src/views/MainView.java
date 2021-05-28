package views;

import javax.swing.*;

import controller.Controller;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class MainView extends javax.swing.JFrame implements Observer {
    public static final Icon CLOSE_TAB_ICON1 = new ImageIcon("src/images/closeTabButton1.png");
    public static final Icon CLOSE_TAB_ICON2 = new ImageIcon("src/images/closeTabButton2.png");
    private final Controller controller;
    private ArrayList<ImagePanel> projects = new ArrayList();
    private RotationPanelView rotationView = null;
    private NewView newView = null;
    private ResizeView resizeView = null;
    private LaysHistView laysHistView = null;
    private TextPanelView textview = null;
    private ToolsPanelView toolsView = null;
    private ColorBalanceView colorBalanceView = null;
    private BrightnessPanelView brightnessView = null;
    private int newProject = 0;

    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        controller = new Controller(this);
        this.addWindowListener(controller);
        tabbedPane.addChangeListener(controller);
        tabbedPane.addMouseListener(controller);
        tabbedPane.addMouseWheelListener(controller);
        InputMap inputMap = tabbedPane.getInputMap();
        KeyStroke left = KeyStroke.getKeyStroke("LEFT");
        KeyStroke right = KeyStroke.getKeyStroke("RIGHT");
        inputMap.put(left, "none");
        inputMap.put(right, "none");
        tabbedPane.addKeyListener(controller);
        menuItemOpen.addActionListener(controller);
        menuItemOpenLayer.addActionListener(controller);
        menuItemNewLayer.addActionListener(controller);
        menuItemClose.addActionListener(controller);
        menuItemCloseAll.addActionListener(controller);
        menuItemQuit.addActionListener(controller);
        menuItemNew.addActionListener(controller);
        menuItemResize.addActionListener(controller);
        menuItemTools.addActionListener(controller);
        menuItemLayers.addActionListener(controller);
        buttonStopFilter.addActionListener(controller);
        menuItemRotation.addActionListener(controller);
        menuItemLayRotate.addActionListener(controller);
        menuItemLayResize.addActionListener(controller);
        spinnerZoom.addChangeListener(controller);
        menuItemHistory.addActionListener(controller);
        menuItemUndo.addActionListener(controller);
        menuItemRedo.addActionListener(controller);
        menuItemSave.addActionListener(controller);
        menuItemCopy.addActionListener(controller);
        menuItemCut.addActionListener(controller);
        menuItemPaste.addActionListener(controller);


        menuItemSelectAll.addActionListener(controller);
        menuItemSelectAll.setEnabled(false);
        menuItemRedo.setEnabled(false);
        menuItemUndo.setEnabled(false);
        labelZoom.setEnabled(false);
        spinnerZoom.setEnabled(false);
        labelZoom2.setEnabled(false);
        buttonStopFilter.setEnabled(false);
        menuItemSave.setEnabled(false);
        menuItemClose.setEnabled(false);
        menuItemCloseAll.setEnabled(false);
        menuItemRotation.setEnabled(false);
        menuItemResize.setEnabled(false);
        menuItemOpenLayer.setEnabled(false);
        menuItemNewLayer.setEnabled(false);
        menuItemLayResize.setEnabled(false);
        menuItemLayRotate.setEnabled(false);
        menuItemNew.setIcon(new ImageIcon("src/images/new.png"));
        menuItemOpen.setIcon(new ImageIcon("src/images/open.png"));
        menuItemSave.setIcon(new ImageIcon("src/images/save.png"));
        menuItemClose.setIcon(new ImageIcon("src/images/close.png"));
        menuItemCloseAll.setIcon(new ImageIcon("src/images/closeall.png"));
        menuItemQuit.setIcon(new ImageIcon("src/images/quit.png"));
        menuItemRotation.setIcon(new ImageIcon("src/images/rotate.png"));
        menuItemResize.setIcon(new ImageIcon("src/images/resize.png"));
        menuItemOpenLayer.setIcon(new ImageIcon("src/images/open.png"));
        menuItemNewLayer.setIcon(new ImageIcon("src/images/layers.png"));
        menuItemLayers.setIcon(new ImageIcon("src/images/layers.png"));
        menuItemTools.setIcon(new ImageIcon("src/images/tools.png"));
        menuItemLayRotate.setIcon(new ImageIcon("src/images/rotate.png"));
        menuItemLayResize.setIcon(new ImageIcon("src/images/resize.png"));
        menuItemHistory.setIcon(new ImageIcon("src/images/history.png"));
        menuItemUndo.setIcon(new ImageIcon("src/images/undo.png"));
        menuItemRedo.setIcon(new ImageIcon("src/images/redo.png"));
        menuItemCopy.setIcon(new ImageIcon("src/images/copy.png"));
        menuItemPaste.setIcon(new ImageIcon("src/images/paste.png"));
        menuItemCut.setIcon(new ImageIcon("src/images/cutbis.png"));


        menuItemSelectAll.setIcon(new ImageIcon("src/images/selectall.png"));

        menuItemBalance.setIcon(new ImageIcon("src/images/color.png"));
        menuItemBalance.addActionListener(controller);
        menuItemBalance.setEnabled(false);
        menuItemBright.setIcon(new ImageIcon("src/images/brightness.png"));
        menuItemBright.addActionListener(controller);
        menuItemBright.setEnabled(false);
        labelX.setText("x: 0");
        labelY.setText("y: 0");
        labelX.setEnabled(false);
        labelY.setEnabled(false);

        newView = new NewView(this,true);
        newView.addWindowListener(controller);
        rotationView = new RotationPanelView(this,false);
        rotationView.addWindowListener(controller);
        resizeView = new ResizeView(this,false);
        resizeView.addWindowListener(controller);
        laysHistView = new LaysHistView(this,false);
        laysHistView.addWindowListener(controller);
        textview = new TextPanelView(this,false);
        textview.addWindowListener(controller);
        toolsView = new ToolsPanelView(this,false);
        toolsView.addWindowListener(controller);
        colorBalanceView = new ColorBalanceView(this,true);
        colorBalanceView.addWindowListener(controller);
        brightnessView = new BrightnessPanelView(this,true);
        brightnessView.addWindowListener(controller);
        menuItemCopy.setEnabled(false);
        menuItemPaste.setEnabled(false);
        menuItemCut.setEnabled(false);
        laysHistView.setVisible(true);
        toolsView.setVisible(true);

        this.setTitle("Project CG & GPI");
        this.setIconImage(new ImageIcon("src/images/icon.png").getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        labelZoom = new javax.swing.JLabel();
        labelZoom2 = new javax.swing.JLabel();
        buttonStopFilter = new javax.swing.JButton();
        tabbedPane = new TabbedPanes();
        spinnerZoom = new javax.swing.JSpinner();
        labelX = new javax.swing.JLabel();
        labelY = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemNew = new javax.swing.JMenuItem();
        menuItemNewLayer = new javax.swing.JMenuItem();
        menuItemOpen = new javax.swing.JMenuItem();
        menuItemOpenLayer = new javax.swing.JMenuItem();


        menuItemSave = new javax.swing.JMenuItem();

        menuItemClose = new javax.swing.JMenuItem();
        menuItemCloseAll = new javax.swing.JMenuItem();
        menuItemQuit = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();
        menuItemUndo = new javax.swing.JMenuItem();
        menuItemRedo = new javax.swing.JMenuItem();
        menuItemCopy = new javax.swing.JMenuItem();
        menuItemCut = new javax.swing.JMenuItem();
        menuItemPaste = new javax.swing.JMenuItem();
        menuItemSelectAll = new javax.swing.JMenuItem();
        menuImage = new javax.swing.JMenu();
        menuItemResize = new javax.swing.JMenuItem();
        menuItemRotation = new javax.swing.JMenuItem();
        menuLayer = new javax.swing.JMenu();
        menuItemLayResize = new javax.swing.JMenuItem();
        menuItemLayRotate = new javax.swing.JMenuItem();
        menuItemBalance = new javax.swing.JMenuItem();
        menuItemBright = new javax.swing.JMenuItem();

        menuWindow = new javax.swing.JMenu();
        menuItemTools = new javax.swing.JMenuItem();
        menuItemLayers = new javax.swing.JMenuItem();
        menuItemHistory = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 200));

        labelZoom.setFont(new java.awt.Font("Times New Roman", 0, 14));
        labelZoom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelZoom.setText("zoom");

        labelZoom2.setFont(new java.awt.Font("Times New Roman", 0, 11));
        labelZoom2.setText("%");

        buttonStopFilter.setFont(new java.awt.Font("Times New Roman", 0, 14));
        buttonStopFilter.setText("stop filter");
        buttonStopFilter.setToolTipText("stop filter execution");

        spinnerZoom.setFont(new java.awt.Font("Times New Roman", 0, 12));
        spinnerZoom.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(100.0f), Float.valueOf(10.0f), Float.valueOf(10000.0f), Float.valueOf(1.0f)));

        labelX.setFont(new java.awt.Font("Times New Roman", 0, 14));

        labelY.setFont(new java.awt.Font("Times New Roman", 0, 14));

        MenuBar.setToolTipText("");
        MenuBar.setFont(new java.awt.Font("Times New Roman", 0, 12));

        menuFile.setMnemonic('F');
        menuFile.setText("File");
        menuFile.setFont(new java.awt.Font("Times New Roman", 0, 12));

        menuItemNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuItemNew.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemNew.setText("New image");
        menuItemNew.setToolTipText("create new project");
        menuFile.add(menuItemNew);

        menuItemNewLayer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemNewLayer.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemNewLayer.setText("New layer");
        menuFile.add(menuItemNewLayer);

        menuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuItemOpen.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemOpen.setText("Open");
        menuItemOpen.setToolTipText("open existing image or project");
        menuFile.add(menuItemOpen);

        menuItemOpenLayer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemOpenLayer.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemOpenLayer.setText("Open as layer");
        menuFile.add(menuItemOpenLayer);


        menuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSave.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemSave.setText("Save");
        menuItemSave.setToolTipText("save current image or project");
        menuFile.add(menuItemSave);



        menuItemClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        menuItemClose.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemClose.setText("Close");
        menuItemClose.setToolTipText("close current project");
        menuFile.add(menuItemClose);

        menuItemCloseAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuItemCloseAll.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemCloseAll.setText("Close all");
        menuItemCloseAll.setToolTipText("close all projects");
        menuFile.add(menuItemCloseAll);

        menuItemQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuItemQuit.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemQuit.setText("Quit");
        menuItemQuit.setToolTipText("quit all projects and application");
        menuItemQuit.setVerifyInputWhenFocusTarget(false);
        menuFile.add(menuItemQuit);
        menuItemQuit.getAccessibleContext().setAccessibleDescription("");

        MenuBar.add(menuFile);

        menuEdit.setMnemonic('E');
        menuEdit.setText("Edit");
        menuEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuEdit.setFont(new java.awt.Font("Times New Roman", 0, 12));

        menuItemUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        menuItemUndo.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemUndo.setText("Undo");
        menuEdit.add(menuItemUndo);

        menuItemRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        menuItemRedo.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemRedo.setText("Redo");
        menuEdit.add(menuItemRedo);

        menuItemCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuItemCopy.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemCopy.setText("Copy");
        menuEdit.add(menuItemCopy);

        menuItemCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        menuItemCut.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemCut.setText("Cut");
        menuEdit.add(menuItemCut);

        menuItemPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        menuItemPaste.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemPaste.setText("Paste");
        menuEdit.add(menuItemPaste);

        menuItemSelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSelectAll.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemSelectAll.setText("Select all");
        menuEdit.add(menuItemSelectAll);

        MenuBar.add(menuEdit);

        menuImage.setMnemonic('I');
        menuImage.setText("Image");
        setFont(menuImage, menuItemResize, menuItemRotation);

        MenuBar.add(menuImage);

        menuLayer.setMnemonic('L');
        menuLayer.setText("Layer");
        setFont(menuLayer, menuItemLayResize, menuItemLayRotate);

        menuItemBalance.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemBalance.setText("Color balance - Alpha");
        menuLayer.add(menuItemBalance);

        menuItemBright.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemBright.setText("Brightness - Contrast");
        menuLayer.add(menuItemBright);

        MenuBar.add(menuLayer);



        menuWindow.setMnemonic('w');
        menuWindow.setText("Windows");
        menuWindow.setToolTipText("");
        menuWindow.setFont(new java.awt.Font("Times New Roman", 0, 12));

        menuItemTools.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        menuItemTools.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemTools.setText("Tools");
        menuWindow.add(menuItemTools);

        menuItemLayers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        menuItemLayers.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemLayers.setText("Layers");
        menuWindow.add(menuItemLayers);

        menuItemHistory.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menuItemHistory.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemHistory.setText("History");
        menuWindow.add(menuItemHistory);

        MenuBar.add(menuWindow);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelZoom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelZoom2)
                                .addGap(35, 35, 35)
                                .addComponent(buttonStopFilter)
                                .addGap(47, 47, 47)
                                .addComponent(labelX, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelY, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(518, Short.MAX_VALUE))
                        .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelZoom)
                                        .addComponent(spinnerZoom)
                                        .addComponent(labelZoom2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonStopFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelX, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                                        .addComponent(labelY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }

    private void setFont(JMenu menuLayer, JMenuItem menuItemLayResize, JMenuItem menuItemLayRotate) {
        menuLayer.setFont(new java.awt.Font("Times New Roman", 0, 12));

        menuItemLayResize.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemLayResize.setText("Resize");
        menuLayer.add(menuItemLayResize);

        menuItemLayRotate.setFont(new java.awt.Font("Times New Roman", 0, 12));
        menuItemLayRotate.setText("Rotate");
        menuLayer.add(menuItemLayRotate);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }



        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    public final void addClosableTab(final TabbedPanes tabbedPane, final JComponent c, Panels pan, final String title,
                                     final Icon icon) {
        if (tabbedPane.getSelectedIndex() == -1) {
            this.enableOnAdding();
        }
        tabbedPane.addTab(null, c);
        final int pos = tabbedPane.indexOfComponent(c);
        FlowLayout f = new FlowLayout(FlowLayout.CENTER, 5, 0);
        final JPanel pnlTab = new JPanel(f);
        pnlTab.setOpaque(false);
        final JLabel lblTitle = new JLabel(title);
        final JButton buttonClose = new JButton();
        buttonClose.setOpaque(false);
        buttonClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonClose.setIcon(CLOSE_TAB_ICON1);
        buttonClose.setBorder(null);
        buttonClose.setFocusable(false);
        pnlTab.add(lblTitle);
        pnlTab.add(buttonClose);
        pan.setButtonClose(buttonClose);
        pan.setTitle(lblTitle);
        pnlTab.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        tabbedPane.setTabComponentAt(pos, pnlTab);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = (new JOptionPane()).showConfirmDialog(null, "Are you sure about closing the current project?" + System.lineSeparator() + "Be sure to save it before closing.", "Close project - Project CG & GPI", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.OK_OPTION)
                {
                    int index = tabbedPane.indexOfComponent(c);
                    if (projects.get(index).getHasRot()) {
                        rotationView.setVisible(false);
                        projects.get(index).setHasRot(false);
                    }
                    if (projects.get(index).getHasResize()) {
                        resizeView.setVisible(false);
                        projects.get(index).setHasResize(false);
                    }
                    projects.remove(index);
                    tabbedPane.remove(c);
                    if (tabbedPane.getSelectedIndex() == -1) {
                        disableOnClosing();
                        laysHistView.getButtonLaysView().setSelected(false);
                        laysHistView.getModelHistory().clear();
                        laysHistView.getModelLayers().clear();
                        laysHistView.getListHistory().repaint();
                        laysHistView.getListLayers().repaint();
                    }
                }
            }
        };

        MouseListener mouseListener = new MouseListener() {

            @Override
            public void mouseEntered(MouseEvent e) {
                buttonClose.setIcon(CLOSE_TAB_ICON2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonClose.setIcon(CLOSE_TAB_ICON1);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getComponent() == lblTitle)
                    tabbedPane.setSelectedComponent(c);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        };

        buttonClose.addActionListener(actionListener);
        buttonClose.addMouseListener(mouseListener);
        pnlTab.addMouseListener(mouseListener);
        lblTitle.addMouseListener(mouseListener);

        // Optionally bring the new tab to the front
        tabbedPane.setSelectedComponent(c);
    }

    public void updateWindow() {
        if (tabbedPane.getSelectedIndex() == -1) {
            this.setTitle("Project CG & GPI");
            this.setIconImage(new ImageIcon("src/images/icon.png").getImage());
        }
        else {
            ImagePanel img = projects.get(tabbedPane.getSelectedIndex());
            this.setTitle("Project CG & GPI - " + img.getFileName() + " (" + img.getImgW() + "x" + img.getImgH() + ")");
            this.setIconImage(img.getBuiltImage());
        }
    }

    public JLabel getLabelX() {
        return labelX;
    }

    public JLabel getLabelY() {
        return labelY;
    }



    public JMenuItem getMenuItemCopy() {
        return menuItemCopy;
    }

    public JMenuItem getMenuItemBalance() {
        return menuItemBalance;
    }


    public JMenuItem getMenuItemSelectAll() {
        return menuItemSelectAll;
    }

    public JMenuItem getMenuItemPaste() {
        return menuItemPaste;
    }

    public JMenuItem getMenuItemCut() {
        return menuItemCut;
    }

    public JMenuItem getMenuOpen() {
        return menuItemOpen;
    }

    public JMenuItem getMenuItemOpenLayer() {
        return menuItemOpenLayer;
    }

    public JMenuItem getMenuItemNewLayer() {
        return menuItemNewLayer;
    }

    public JMenuItem getMenuItemHistory() {
        return menuItemHistory;
    }

    public JMenuItem getMenuItemLayers() {
        return menuItemLayers;
    }

    public JMenuItem getMenuItemTools() {
        return menuItemTools;
    }



    public TabbedPanes getTabs() {
        return tabbedPane;
    }

    public ArrayList<ImagePanel> getProjects() {
        return projects;
    }

    public JButton getStopFilter() {
        return buttonStopFilter;
    }

    public Controller getController() {
        return controller;
    }

    public JSpinner getSpangle() {
        return rotationView.getSpinnerAngle();
    }

    public JSlider getSliderAngle() {
        return rotationView.getSliderAngle();
    }

    public JSpinner getSpinnerZoom() {
        return spinnerZoom;
    }

    public JLabel getLabelZoom() {
        return labelZoom;
    }

    public JMenuItem getMenuItemRotation() {
        return menuItemRotation;
    }

    public JMenuItem getMenuItemResize() {
        return menuItemResize;
    }

    public JMenuItem getMenuItemBright() {
        return menuItemBright;
    }

    public JMenuItem getMenuItemLayResize() {
        return menuItemLayResize;
    }

    public JMenuItem getMenuItemLayRotate() {
        return menuItemLayRotate;
    }

    public void setRotationView(RotationPanelView rotationView) {
        this.rotationView = rotationView;
    }

    public BrightnessPanelView getBrightView() {
        return brightnessView;
    }

    public RotationPanelView getRotView() {
        return rotationView;
    }

    public ToolsPanelView getToolsView() {
        return toolsView;
    }

    public void setResizeView(ResizeView resizeview) {
        resizeView = resizeview;
    }

    public ResizeView getResizeView() {
        return resizeView;
    }

    public ColorBalanceView getColorBalanceView() {
        return colorBalanceView;
    }

    public JMenuItem getMenuItemNew() {
        return menuItemNew;
    }

    public JMenuItem getMenuItemClose() {
        return menuItemClose;
    }

    public JMenuItem getMenuItemCloseAll() {
        return menuItemCloseAll;
    }

    public JMenuItem getMenuItemQuit() {
        return menuItemQuit;
    }

    public JMenuItem getMenuItemUndo() {
        return menuItemUndo;
    }

    public JMenuItem getMenuItemRedo() {
        return menuItemRedo;
    }

    public JMenuItem getMenuItemSave() {
        return menuItemSave;
    }

    public NewView getNewView() {
        return newView;
    }

    public int getNewProject() {
        return newProject;
    }

    public void incrementNewProject() {
        newProject++;
    }

    public LaysHistView getLaysView() {
        return laysHistView;
    }

    public TextPanelView getTextView() {
        return textview;
    }

    public JButton getButtonStopFilter() {
        return buttonStopFilter;
    }

    public void enableOnAdding() {
        labelZoom.setEnabled(true);
        spinnerZoom.setEnabled(true);
        labelZoom2.setEnabled(true);
        labelX.setEnabled(true);
        labelY.setEnabled(true);

        menuItemSave.setEnabled(true);
        menuItemClose.setEnabled(true);
        menuItemCloseAll.setEnabled(true);
        menuItemRotation.setEnabled(true);
        menuItemResize.setEnabled(true);
        menuItemOpenLayer.setEnabled(true);
        menuItemNewLayer.setEnabled(true);
        menuItemLayResize.setEnabled(true);
        menuItemLayRotate.setEnabled(true);
        menuItemRedo.setEnabled(true);
        menuItemUndo.setEnabled(true);
        menuItemCopy.setEnabled(true);
        menuItemPaste.setEnabled(true);
        menuItemCut.setEnabled(true);

        menuItemSelectAll.setEnabled(true);

        menuItemBalance.setEnabled(true);
        menuItemBright.setEnabled(true);
        laysHistView.getSliderLaysAlpha().setValue(100);
    }

    public void disableOnClosing() {
        labelZoom.setEnabled(false);
        spinnerZoom.setEnabled(false);
        labelZoom2.setEnabled(false);
        labelX.setEnabled(false);
        labelY.setEnabled(false);

        menuItemSave.setEnabled(false);
        menuItemClose.setEnabled(false);
        menuItemCloseAll.setEnabled(false);
        buttonStopFilter.setEnabled(false);
        menuItemRotation.setEnabled(false);
        menuItemResize.setEnabled(false);
        menuItemOpenLayer.setEnabled(false);
        menuItemNewLayer.setEnabled(false);
        menuItemLayResize.setEnabled(false);
        menuItemLayRotate.setEnabled(false);
        menuItemRedo.setEnabled(false);
        menuItemUndo.setEnabled(false);
        menuItemCopy.setEnabled(false);
        menuItemPaste.setEnabled(false);
        menuItemCut.setEnabled(false);

        menuItemSelectAll.setEnabled(false);

        menuItemBalance.setEnabled(false);
        menuItemBright.setEnabled(false);
        laysHistView.getSliderLaysAlpha().setValue(100);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonStopFilter;
    private javax.swing.JLabel labelX;
    private javax.swing.JLabel labelY;
    private javax.swing.JLabel labelZoom;
    private javax.swing.JLabel labelZoom2;
    private javax.swing.JMenuItem menuItemBalance;
    private javax.swing.JMenuItem menuItemBright;
    private javax.swing.JMenuItem menuItemClose;
    private javax.swing.JMenuItem menuItemCloseAll;
    private javax.swing.JMenuItem menuItemCopy;
    private javax.swing.JMenuItem menuItemCut;
    private javax.swing.JMenuItem menuItemHistory;
    private javax.swing.JMenuItem menuItemLayResize;
    private javax.swing.JMenuItem menuItemLayRotate;
    private javax.swing.JMenuItem menuItemLayers;

    private javax.swing.JMenuItem menuItemNew;
    private javax.swing.JMenuItem menuItemNewLayer;
    private javax.swing.JMenuItem menuItemOpen;
    private javax.swing.JMenuItem menuItemOpenLayer;

    private javax.swing.JMenuItem menuItemPaste;

    private javax.swing.JMenuItem menuItemQuit;
    private javax.swing.JMenuItem menuItemRedo;
    private javax.swing.JMenuItem menuItemResize;
    private javax.swing.JMenuItem menuItemRotation;
    private javax.swing.JMenuItem menuItemSave;
    private javax.swing.JMenuItem menuItemSelectAll;
    private javax.swing.JMenuItem menuItemTools;
    private javax.swing.JMenuItem menuItemUndo;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu menuFile;

    private javax.swing.JMenu menuImage;
    private javax.swing.JMenu menuLayer;
    private javax.swing.JMenu menuWindow;
    private javax.swing.JSpinner spinnerZoom;
    private TabbedPanes tabbedPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable obs, Object o) {
        JMenuItem mi = null;
        projects.get(tabbedPane.getSelectedIndex()).getParent().getParent().validate();
    }
}
