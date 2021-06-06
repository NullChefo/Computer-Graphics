


package controller;

// region imports
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.util.zip.GZIPOutputStream;

import models.CareTaker;
import models.Moments;
import models.Model;
import models.Creator;
import shape.*;
import views.ImagePanel;
import views.LayerPanel;
import views.MainView;
import views.FileFilters;
import views.Panels;
import views.TabbedPanes;

import static shape.Star.createDefStar;
//endregion

public class Controller implements ActionListener, MouseListener, ChangeListener, WindowListener, KeyListener, ListSelectionListener, MouseMotionListener, MouseWheelListener {
    private MainView mainview;
    private Model model;
    private final JFileChooser fileChooser;
    private boolean newProject = true;
    private JToggleButton toggleButton = null;
    private Font fontText = new Font("Times New Roman", Font.PLAIN, 12);
    private Color col = Color.BLACK;
    private String addText = "";
    boolean compress = false;

    private Creator originator = new Creator();
    private boolean moving = false;
    private boolean hasDraw = false;
    private boolean hasPaint = false;
    private boolean hasClear = false;
    private boolean hasRect = false;
    private boolean hasOval = false;



    private boolean hasStar = false;
    private boolean hasHexagon = false;
    private boolean hasParallelogram = false;
    private boolean hasPentagon = false;
    private boolean hasTrapezoid = false;
    private boolean hasTriangle = false;
    private boolean hasHearth = false;
    private boolean hasCircle3 = false;

//#TODO add more primitives

    private boolean hasLine = false;
    private boolean hasFill = false;
    private boolean hasCut = false;
    private boolean hasSelect = false;
    private boolean hasPipette = false;
    private BufferedImage imgSelect = null;

    private int prevp = 100;

    public Controller(MainView mainView) {
        mainview = mainView;
        model = new Model(mainview);
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileFilters());
    }

    public void addObserver(Observer o) {
        model.addObserver(o);
    }

    public BufferedImage getImgSelect() {
        return imgSelect;
    }

    public MainView getMainView() {
        return mainview;
    }

    public boolean getHasDraw() {
        return hasDraw;
    }


    public boolean getHasPaint() {
        return hasPaint;
    }

    public boolean getHasClear() {
        return hasClear;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass() == JMenuItem.class) {
            JMenuItem menuItem = (JMenuItem)e.getSource();
            JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
            JMenu menu = (JMenu) popupMenu.getInvoker();

            if (menuItem == mainview.getMenuOpen())
                try {
                    this.openProject();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            else if (menuItem == mainview.getMenuItemOpenLayer())
                this.openLayer();

            else if (menuItem == mainview.getMenuItemTools()) {
                mainview.getToolsView().setVisible(true);
            }
            else if (menuItem == mainview.getMenuItemLayers()) {
                mainview.getLaysView().setVisible(true);
                mainview.getLaysView().getTabbedPaneLaysHist().setSelectedIndex(0);
            }
            else if (menuItem == mainview.getMenuItemHistory()) {
                mainview.getLaysView().setVisible(true);
                mainview.getLaysView().getTabbedPaneLaysHist().setSelectedIndex(1);
            }

            //Save

            else if (menuItem == mainview.getMenuItemSave()) {
                fileChooser.setCurrentDirectory(new File(mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).getFilePath()));
                BufferedImage image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).getBuiltImage();
                BufferedImage imageSave = image;
                int ret = fileChooser.showSaveDialog(mainview);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File fileChooserSelectedFile = fileChooser.getSelectedFile();
                    String filename = fileChooserSelectedFile.getPath();
                    String name = fileChooserSelectedFile.getName();
                    String fileExtension = "png";
                    if (filename.toLowerCase().endsWith(".png")) {
                        fileExtension = "PNG";
                    }
                    else if (filename.toLowerCase().endsWith(".jpg")) {
                        fileExtension = "JPG";
                        imageSave = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
                        Graphics2D graphics2D = imageSave.createGraphics();
                        graphics2D.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                    }
                    else if (filename.toLowerCase().endsWith(".gif")) {
                        fileExtension = "GIF";
                    }
                    else if (filename.toLowerCase().endsWith(".bmp")) {
                        fileExtension = "BMP";
                        imageSave = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
                        Graphics2D graphics2D = imageSave.createGraphics();
                        graphics2D.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                    }
                    else if (filename.toLowerCase().endsWith(".proj"))
                        fileExtension = "PROJ";

                    else if (filename.toLowerCase().endsWith(".proj.gz")) {
                        fileExtension = "GZ";
                        compress = true;
                    }
                    else {
                        filename = filename + ".png";
                    }

                    if (fileExtension.compareTo("PROJ") == 0) {
                        ImagePanel imageP = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                        ImagePanel imageSav;
                        File file = new File(filename);
                        try {
                            ObjectOutputStream objectOutputStream =  new ObjectOutputStream(new FileOutputStream(file));
                            objectOutputStream.writeObject(imageP.getFileName());
                            objectOutputStream.writeObject(imageP.getFilePath());
                            objectOutputStream.writeObject(imageP.getZoom());
                            objectOutputStream.writeObject(imageP.getImgW());
                            objectOutputStream.writeObject(imageP.getImgH());
                            objectOutputStream.writeObject(imageP.getNbLayers());
                            objectOutputStream.writeObject(imageP.getIndexSelect());
                            imageP.buildLayersPix();
                            objectOutputStream.writeObject(imageP.getLayers());
                            objectOutputStream.writeObject(imageP.getCareTaker().getStates().size());
                            for (Moments moments : imageP.getCareTaker().getStates()) {
                                objectOutputStream.writeObject(moments.getSavedState());
                                objectOutputStream.writeObject(moments.getIconPix());
                                imageSav = moments.getSavedImg();
                                objectOutputStream.writeObject(imageSav.getFileName());
                                objectOutputStream.writeObject(imageSav.getFilePath());
                                objectOutputStream.writeObject(imageSav.getZoom());
                                objectOutputStream.writeObject(imageSav.getImgW());
                                objectOutputStream.writeObject(imageSav.getImgH());
                                objectOutputStream.writeObject(imageSav.getNbLayers());
                                objectOutputStream.writeObject(imageSav.getIndexSelect());
                                objectOutputStream.writeObject(imageSav.getLayers());
                            }
                            objectOutputStream.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                    // Added compressed version of the project file format ".proj.gz"
                    // This was added because the .proj files gets actually big if you trying to work with
                    // Large pixel density

                    else if (fileExtension.compareTo("GZ") == 0) {
                        ImagePanel imagePanel = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                        ImagePanel imageSav;
                        FileOutputStream fileOutputStream;
                        GZIPOutputStream gzipOutputStream;
                        ObjectOutputStream objectOutputStream;
                        try {
                            fileOutputStream = new FileOutputStream(filename);
                            gzipOutputStream = new GZIPOutputStream(fileOutputStream);
                            objectOutputStream = new ObjectOutputStream(gzipOutputStream);
                            objectOutputStream.writeObject(imagePanel.getFileName());
                            objectOutputStream.writeObject(imagePanel.getFilePath());
                            objectOutputStream.writeObject(imagePanel.getZoom());
                            objectOutputStream.writeObject(imagePanel.getImgW());
                            objectOutputStream.writeObject(imagePanel.getImgH());
                            objectOutputStream.writeObject(imagePanel.getNbLayers());
                            objectOutputStream.writeObject(imagePanel.getIndexSelect());
                            imagePanel.buildLayersPix();
                            objectOutputStream.writeObject(imagePanel.getLayers());
                            objectOutputStream.writeObject(imagePanel.getCareTaker().getStates().size());
                            for (Moments moments : imagePanel.getCareTaker().getStates()) {
                                objectOutputStream.writeObject(moments.getSavedState());
                                objectOutputStream.writeObject(moments.getIconPix());
                                imageSav = moments.getSavedImg();
                                objectOutputStream.writeObject(imageSav.getFileName());
                                objectOutputStream.writeObject(imageSav.getFilePath());
                                objectOutputStream.writeObject(imageSav.getZoom());
                                objectOutputStream.writeObject(imageSav.getImgW());
                                objectOutputStream.writeObject(imageSav.getImgH());
                                objectOutputStream.writeObject(imageSav.getNbLayers());
                                objectOutputStream.writeObject(imageSav.getIndexSelect());
                                objectOutputStream.writeObject(imageSav.getLayers());
                            }
                            objectOutputStream.flush();
                            objectOutputStream.close();
                            gzipOutputStream.close();
                            fileOutputStream.close();
                        } catch(IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                    else {
                        File outputFile = new File(filename);
                        try {
                            ImageIO.write(imageSave, fileExtension, outputFile);
                        } catch (IOException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    ImagePanel img = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    img.setFileName(name);
                    ((Panels) img.getParent()).getTitle().setText(name);
                    mainview.updateWindow();
                }
            }

            //Copy

            else if (menuItem == mainview.getMenuItemCopy() && mainview.getTabs().getSelectedIndex() != -1) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                LayerPanel layer = image.getLayers().get(image.getIndexSelect());
                BufferedImage bufferedImage = layer.getImage();
                int ix = (int)((float)image.getInitX()*100/image.getZoom())- layer.getLocalX();
                int fx = (int)((float)image.getCursorX()*100/image.getZoom())- layer.getLocalX();
                int iy = (int)((float)image.getInitY()*100/image.getZoom())- layer.getLocalY();
                int fy = (int)((float)image.getCursorY()*100/image.getZoom())- layer.getLocalY();
                if (ix > fx) {
                    int tmp = fx;
                    fx = ix;
                    ix = tmp;
                }
                if (iy > fy) {
                    int tmp = fy;
                    fy = iy;
                    iy = tmp;
                }
                if (fx > 0 && fy > 0 && ix < bufferedImage.getWidth()-1 && iy < bufferedImage.getHeight()-1) {
                    ix = Math.max(ix, 0);
                    iy = Math.max(iy, 0);
                    fx = Math.min(fx, bufferedImage.getWidth() - 1);
                    fy = Math.min(fy, bufferedImage.getHeight() - 1);
                    int w = Math.abs(fx-ix);
                    int h = Math.abs(fy-iy);
                    imgSelect = new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR);
                    for (int y = 0; y < h; y++) {
                        for (int x = 0; x < w; x++) {
                            imgSelect.setRGB(x, y, bufferedImage.getRGB(ix+x, iy+y));
                        }
                    }
                    image.setHasSelect(false);
                    image.repaint();

                }

                //Cut

            }else if (menuItem == mainview.getMenuItemCut() && mainview.getTabs().getSelectedIndex() != -1) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                LayerPanel layer = image.getLayers().get(image.getIndexSelect());
                BufferedImage bufferedImage = layer.getImage();
                int iX = (int)((float)image.getInitX()*100/image.getZoom())- layer.getLocalX();
                int fX = (int)((float)image.getCursorX()*100/image.getZoom())- layer.getLocalX();
                int iY = (int)((float)image.getInitY()*100/image.getZoom())- layer.getLocalY();
                int fY = (int)((float)image.getCursorY()*100/image.getZoom())- layer.getLocalY();
                if (iX > fX) {
                    int tmp = fX;
                    fX = iX;
                    iX = tmp;
                }
                if (iY > fY) {
                    int tmp = fY;
                    fY = iY;
                    iY = tmp;
                }
                if (fX > 0 && fY > 0 && iX < bufferedImage.getWidth()-1 && iY < bufferedImage.getHeight()-1) {
                    iX = Math.max(iX, 0);
                    iY = Math.max(iY, 0);
                    fX = Math.min(fX, bufferedImage.getWidth() - 1);
                    fY = Math.min(fY, bufferedImage.getHeight() - 1);
                    int w = Math.abs(fX - iX);
                    int h = Math.abs(fY - iY);
                    int argb = new Color(255,255,255,0).getRGB();
                    imgSelect = new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR);
                    for (int y = 0; y < h; y++) {
                        for (int x = 0; x < w; x++) {
                            imgSelect.setRGB(x, y, bufferedImage.getRGB(iX +x, iY +y));
                        }
                    }
                    for (int y = iY; y < iY +h; y++) {
                        for (int x = iX; x < iX +w; x++) {
                            bufferedImage.setRGB(x, y, argb);
                        }
                    }
                    image.setHasSelect(false);

                    mainview.updateWindow();
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Cut from layer: "+ layer.getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }


            //Paste

            else if (menuItem == mainview.getMenuItemPaste() && mainview.getTabs().getSelectedIndex() != -1) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                BufferedImage bufferedImage = new BufferedImage(imgSelect.getWidth(), imgSelect.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                bufferedImage.createGraphics().drawImage(imgSelect, 0, 0, null);
                image.newLayer(bufferedImage);
                this.updateLayers();
                mainview.updateWindow();
                image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Paste new layer: "+image.getLayers().get(image.getLayers().size()-1).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                this.updateHist();
                mainview.getTabs().grabFocus();
            }
            else if (menuItem == mainview.getMenuItemSelectAll() && mainview.getTabs().getSelectedIndex() != -1) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                LayerPanel layer = image.getLayers().get(image.getIndexSelect());
                image.setInitX(0);
                image.setInitY(0);
                image.setCursorX(image.getWidth());
                image.setCursorY(image.getHeight());
                Graphics g = image.getGraphics();
                g.setColor(new Color(0,255,0,255));
                int ix = (int)((float) layer.getLocalX()*image.getZoom()/100);
                int iy = (int)((float) layer.getLocalY()*image.getZoom()/100);
                int iw = (int)((float) layer.getImage().getWidth()*image.getZoom()/100);
                int ih = (int)((float) layer.getImage().getHeight()*image.getZoom()/100);
                g.drawRect(ix, iy, iw+2, ih+2);
            }

            //Rotate

            else if (menuItem == mainview.getMenuItemRotation()) {
                String angle = (new JOptionPane()).showInputDialog(null, "Enter rotation angle in degrees", "Rotate image - Project CG & GPI", JOptionPane.QUESTION_MESSAGE);
                if (angle != null && angle.matches("-?[0-9]+(.[0-9]+)?")) {
                    ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    image.setRotateProj(true);
                    image.setHasRot(true);
                    image.rotateExec(Float.valueOf(angle));
                    image.setRotateProj(false);
                    image.setHasRot(false);
                    this.updateLayers();
                    mainview.updateWindow();
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Rotate image to "+ angle,image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }
            else if (menuItem == mainview.getMenuItemLayRotate()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                image.setRotateProj(false);
                image.setHasRot(true);
                mainview.getRotView().setTitle("Rotate - " + image.getLayers().get(image.getIndexSelect()).getFileName());
                mainview.getRotView().setVisible(true);
                mainview.getRotView().getButtonReset().doClick();
            }
            else if (menuItem == mainview.getMenuItemResize()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                image.setHasResize(true);
                image.setResizeProj(true);
                mainview.getResizeView().setTitle("Resize Image");
                mainview.getResizeView().setVisible(true);
                mainview.getResizeView().getButtonResizeReset().doClick();
            }
            else if (menuItem == mainview.getMenuItemLayResize()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                image.setResizeProj(false);
                image.setHasResize(true);
                mainview.getResizeView().setTitle("Resize - " + image.getLayers().get(image.getIndexSelect()).getFileName());
                mainview.getResizeView().setVisible(true);
                mainview.getResizeView().getButtonResizeReset().doClick();
            }
            else if (menuItem == mainview.getMenuItemClose())
                ((Panels)mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).getParent()).getButtonClose().doClick();
            else if (menuItem == mainview.getMenuItemCloseAll()) {
                while (mainview.getTabs().getSelectedIndex() != -1)
                    ((Panels)mainview.getProjects().get(0).getParent()).getButtonClose().doClick();
            }
            else if (menuItem == mainview.getMenuItemQuit()) {
                int option = (new JOptionPane()).showConfirmDialog(null, "Are you sure you want to quit the application?" + System.lineSeparator() + "Be sure to save all opened projects before leaving.", "Quit Project CG & GPI", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.OK_OPTION)
                    System.exit(0);
            }
            else if (menuItem == mainview.getMenuItemNew()) {
                newProject = true;
                mainview.getNewView().setVisible(true);
            }
            else if (menuItem == mainview.getMenuItemNewLayer()) {
                newProject = false;
                mainview.getNewView().setVisible(true);
            }
            else if (menuItem == mainview.getMenuItemUndo()) {
                int index = mainview.getLaysView().getListHistory().getSelectedIndex();
                if (index > 0) {
                    mainview.getLaysView().getListHistory().setSelectedIndex(index-1);
                    mainview.getTabs().grabFocus();
                }
            }
            else if (menuItem == mainview.getMenuItemRedo()) {
                int index = mainview.getLaysView().getListHistory().getSelectedIndex();
                int size = mainview.getLaysView().getModelHistory().size();
                if (index < size-1) {
                    mainview.getLaysView().getListHistory().setSelectedIndex(index+1);
                    mainview.getTabs().grabFocus();
                }
            }

            //Balance


            else if (menuItem == mainview.getMenuItemBalance()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                BufferedImage layerBuffer = image.getLayers().get(image.getIndexSelect()).getImage();
                BufferedImage imageColor = new BufferedImage(layerBuffer.getWidth(), layerBuffer.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                imageColor.createGraphics().drawImage(layerBuffer, 0, 0, null);
                image.setImgCol(imageColor);
                image.setHasBalance(true);
                mainview.getColorBalanceView().setVisible(true);
                mainview.getColorBalanceView().setTitle("Color balance "+image.getLayers().get(image.getIndexSelect()).getFileName());
                mainview.getColorBalanceView().getSliderRed().setValue(100);
                mainview.getColorBalanceView().getSliderGreen().setValue(100);
                mainview.getColorBalanceView().getSliderBlue().setValue(100);
                mainview.getColorBalanceView().getSliderAlpha().setValue(100);
            }

            //Bright

            else if (menuItem == mainview.getMenuItemBright()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                BufferedImage layerBuffer = image.getLayers().get(image.getIndexSelect()).getImage();
                BufferedImage imageColor = new BufferedImage(layerBuffer.getWidth(), layerBuffer.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                imageColor.createGraphics().drawImage(layerBuffer, 0, 0, null);
                image.setImgCol(imageColor);
                image.setHasBright(true);
                mainview.getBrightView().setVisible(true);
                mainview.getBrightView().setTitle("Brightness Contrast "+image.getLayers().get(image.getIndexSelect()).getFileName());
                mainview.getBrightView().getSliderBright().setValue(100);
                mainview.getBrightView().getSliderContrast().setValue(100);
            }
        }
        else if (e.getSource().getClass() == JButton.class) {
            JButton button = (JButton)e.getSource();

             if (button == mainview.getRotView().getButtonRotation()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                ((Panels)image.getParent()).clearRect();
                image.rotateExec((float)mainview.getRotView().getSpinnerAngle().getValue());
                mainview.getRotView().setVisible(false);
                image.setHasRot(false);

                this.updateLayers();
                mainview.updateWindow();
                if (image.getRotateProj())
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Rotate image to "+(float)mainview.getRotView().getSpinnerAngle().getValue(),image.getImagePanelCopy(),image.getBuiltImage()));
                else
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Rotate "+image.getLayers().get(image.getIndexSelect()).getFileName()+" to "+(float)mainview.getRotView().getSpinnerAngle().getValue(),image.getImagePanelCopy(),image.getBuiltImage()));
                this.updateHist();
                mainview.getTabs().grabFocus();
            }
            else if (button == mainview.getRotView().getButtonReset()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                mainview.getRotView().getSpinnerAngle().setValue((float) 0);
                try {
                    mainview.getRotView().getSpinnerAngle().commitEdit();
                } catch (ParseException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (button == mainview.getRotView().getButtonCancel()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                if(image.getRotateProj())
                {
                    ((Panels)image.getParent()).clearRect();
                    image.setHasRot(false);
                    image.getParent().getParent().validate();
                    mainview.getRotView().setVisible(false);
                }
                else {
                    image.setHasRot(false);
                    image.repaint();
                    mainview.getRotView().setVisible(false);
                }
            }

            //Resize view

            else if (button == mainview.getResizeView().getButtonResizeResize()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                image.resizeExec((int)mainview.getResizeView().getSpinnerResizeWidth().getValue(),(int)mainview.getResizeView().getSpinnerResizeHeight().getValue());
                mainview.getResizeView().setVisible(false);
                image.setHasResize(false);
                this.updateLayers();
                mainview.updateWindow();
                if (image.getResizeProj())
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Resize image to "+(int)mainview.getResizeView().getSpinnerResizeWidth().getValue()+"x"+(int)mainview.getResizeView().getSpinnerResizeHeight().getValue(),image.getImagePanelCopy(),image.getBuiltImage()));
                else
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Resize "+image.getLayers().get(image.getIndexSelect()).getFileName()+" to "+(int)mainview.getResizeView().getSpinnerResizeWidth().getValue()+"x"+(int)mainview.getResizeView().getSpinnerResizeHeight().getValue(),image.getImagePanelCopy(),image.getBuiltImage()));
                this.updateHist();
                mainview.getTabs().grabFocus();
            }
            else if (button == mainview.getResizeView().getButtonResizeReset()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                mainview.getResizeView().setBind(true);
                mainview.getResizeView().getButtonResizeBind().setIcon(new ImageIcon("src/images/bind.png"));
                if (image.getResizeProj()) {
                    mainview.getResizeView().getSpinnerResizeWidth().setValue(image.getImgW());
                }
                else {
                    mainview.getResizeView().getSpinnerResizeWidth().setValue(image.getLayers().get(image.getIndexSelect()).getImage().getWidth());
                }
            }
            else if (button == mainview.getResizeView().getButtonResizeBind()) {
                model.sendNotification(e.getSource());
            }
            else if (button == mainview.getNewView().getButtonNewCancel()) {
                mainview.getNewView().setVisible(false);
            }
            else if (button == mainview.getNewView().getButtonNewCreate()) {
                int nw = (int)mainview.getNewView().getSpinnerNewWidth().getValue();
                int nh = (int)mainview.getNewView().getSpinnerNewHeight().getValue();
                Color col = mainview.getNewView().getColorChooser().getColor();
                BufferedImage bufImg = new BufferedImage(nw,nh,BufferedImage.TYPE_4BYTE_ABGR);
                model.fillImageColor(bufImg, col.getRGB());
                if (newProject)
                {
                    mainview.incrementNewProject();
                    ImagePanel image = new ImagePanel(bufImg, "newImage" + mainview.getNewProject(), this);
                    image.addMouseListener(this);
                    image.addMouseMotionListener(this);
                    image.addMouseWheelListener(this);
                    JScrollPane sp = new JScrollPane();
                    Panels pan = new Panels(image);
                    pan.addMouseListener(this);
                    pan.addMouseWheelListener(this);
                    pan.setLayout(new GridBagLayout());
                    pan.add(image);
                    mainview.getProjects().add(image);
                    sp.setViewportView(pan);
                    mainview.addClosableTab(mainview.getTabs(), sp, pan, "newImage" + mainview.getNewProject(), MainView.CLOSE_TAB_ICON1);
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("New image: "+"newImage" + mainview.getNewProject(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
                else {
                    ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    image.newLayer(bufImg);
                    this.updateLayers();
                    mainview.updateWindow();
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("New layer: "+image.getLayers().get(image.getLayers().size()-1).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
                mainview.getNewView().setVisible(false);
            }
            else if (button == mainview.getLaysView().getButtonLaysRemove() && mainview.getTabs().getSelectedIndex() != -1) {
                int index = mainview.getLaysView().getListLayers().getSelectedIndex();
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                String layerName = image.getLayers().get(index).getFileName();
                if (image.getLayers().size() > 1) {
                    image.getLayers().remove(index);
                    image.setIndexSelect(0);
                    this.updateLayers();
                    mainview.updateWindow();
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Remove layer: "+ layerName,image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }
            else if (button == mainview.getLaysView().getButtonLaysUp() && mainview.getTabs().getSelectedIndex() != -1) {
                int index = mainview.getLaysView().getListLayers().getSelectedIndex();
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                if (index != 0) {
                    LayerPanel lp = image.getLayers().get(index);
                    image.getLayers().set(index, image.getLayers().get(index-1));
                    image.getLayers().set(index-1, lp);
                    image.setIndexSelect(index-1);
                    this.updateLayers();
                    mainview.updateWindow();
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Up layer: "+lp.getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }
            else if (button == mainview.getLaysView().getButtonLaysDown() && mainview.getTabs().getSelectedIndex() != -1) {
                int index = mainview.getLaysView().getListLayers().getSelectedIndex();
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                if (index != image.getLayers().size()-1) {
                    LayerPanel lp = image.getLayers().get(index);
                    image.getLayers().set(index, image.getLayers().get(index+1));
                    image.getLayers().set(index+1, lp);
                    image.setIndexSelect(index+1);
                    this.updateLayers();
                    mainview.updateWindow();
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Down layer: "+lp.getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }
            else if (button == mainview.getLaysView().getButtonLaysMergeDown() && mainview.getTabs().getSelectedIndex() != -1) {
                int index = mainview.getLaysView().getListLayers().getSelectedIndex();
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                if (index != image.getLayers().size()-1) {
                    LayerPanel layer = image.getLayers().get(index);
                    BufferedImage bufferedImage = layer.getImage();
                    LayerPanel layerDown = image.getLayers().get(index+1);
                    BufferedImage bufferDown = layerDown.getImage();
                    int nW = Math.max(bufferedImage.getWidth(), bufferDown.getWidth());
                    int nH = Math.max(bufferedImage.getWidth(), bufferDown.getWidth());
                    int nX = Math.min(layer.getLocalX(), layerDown.getLocalX());
                    int nY = Math.min(layer.getLocalY(), layerDown.getLocalY());
                    BufferedImage newBuf = new BufferedImage(nW, nH,BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D graphics2D = newBuf.createGraphics();
                    int imageX = (layer.getLocalX() < layerDown.getLocalX()) ? 0 : layer.getLocalX()- layerDown.getLocalX();
                    int imageY = (layer.getLocalY() < layerDown.getLocalY()) ? 0 : layer.getLocalY()- layerDown.getLocalY();
                    graphics2D.drawImage(bufferedImage, imageX, imageY, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
                    imageX = (layerDown.getLocalX() < layer.getLocalX()) ? 0 : layerDown.getLocalX()- layer.getLocalX();
                    imageY = (layerDown.getLocalY() < layer.getLocalY()) ? 0 : layerDown.getLocalY()- layer.getLocalY();
                    graphics2D.drawImage(bufferDown, imageX, imageY, bufferDown.getWidth(), bufferDown.getHeight(), null);
                    layer.setImage(newBuf);
                    layer.setLocalX(nX);
                    layer.setLocalY(nY);
                    image.getLayers().remove(index+1);
                    image.setIndexSelect(index);
                    this.updateLayers();
                    mainview.updateWindow();
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Merge down "+ layer.getFileName()+" to "+ layerDown.getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }
            else if (button == mainview.getLaysView().getButtonLaysMergeUp() && mainview.getTabs().getSelectedIndex() != -1) {
                int index = mainview.getLaysView().getListLayers().getSelectedIndex();
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                if (index != 0) {
                    LayerPanel lpDown = image.getLayers().get(index);
                    BufferedImage bufferDown = lpDown.getImage();
                    LayerPanel layerP = image.getLayers().get(index-1);
                    BufferedImage bufferUp = layerP.getImage();
                    int nw = Math.max(bufferUp.getWidth(), bufferDown.getWidth());
                    int nh = Math.max(bufferUp.getWidth(), bufferDown.getWidth());
                    int nx = Math.min(layerP.getLocalX(), lpDown.getLocalX());
                    int ny = Math.min(layerP.getLocalY(), lpDown.getLocalY());
                    BufferedImage newBuf = new BufferedImage(nw,nh,BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D graphics2D = newBuf.createGraphics();
                    int imgX = (layerP.getLocalX() < lpDown.getLocalX()) ? 0 : layerP.getLocalX()- lpDown.getLocalX();
                    int imgY = (layerP.getLocalY() < lpDown.getLocalY()) ? 0 : layerP.getLocalY()- lpDown.getLocalY();
                    graphics2D.drawImage(bufferUp, imgX, imgY, bufferUp.getWidth(), bufferUp.getHeight(), null);
                    imgX = (lpDown.getLocalX() < layerP.getLocalX()) ? 0 : lpDown.getLocalX()- layerP.getLocalX();
                    imgY = (lpDown.getLocalY() < layerP.getLocalY()) ? 0 : lpDown.getLocalY()- layerP.getLocalY();
                    graphics2D.drawImage(bufferDown, imgX, imgY, bufferDown.getWidth(), bufferDown.getHeight(), null);
                    layerP.setImage(newBuf);
                    layerP.setLocalX(nx);
                    layerP.setLocalY(ny);
                    image.getLayers().remove(index);
                    image.setIndexSelect(index-1);
                    this.updateLayers();
                    mainview.updateWindow();
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Merge up "+ lpDown.getFileName()+" to "+ layerP.getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }
            else if (button == mainview.getToolsView().getButtonToolColor()) {
                mainview.getToolsView().getCcView().setVisible(true);
            }
            else if (button == mainview.getTextView().getButtonFontCreate()) {
                if (addText.length() > 0) {
                    ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    Canvas c = new Canvas();
                    FontMetrics fm = c.getFontMetrics(fontText);
                    int txtW = fm.stringWidth(addText);
                    int txtH = fm.getHeight();
                    BufferedImage bufferedImage = new BufferedImage(txtW, txtH,BufferedImage.TYPE_4BYTE_ABGR);
                    model.fillImageColor(bufferedImage, new Color(255,255,255,0).getRGB());
                    Graphics2D graphics2D = bufferedImage.createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setFont(fontText);
                    graphics2D.drawString(addText, 0, txtH -fm.getDescent());
                    mainview.getTextView().setVisible(false);
                    image.setHasText(false);
                    image.newLayer(bufferedImage,
                            image.getTextX()-2,image.getTextY()- txtH +fm.getDescent()-2);
                    this.updateLayers();
                    mainview.updateWindow();
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("New text layer: "+ addText,image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }

            //Text view

            else if (button == mainview.getTextView().getButtonFontCancel()) {
                mainview.getTextView().setVisible(false);
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).setHasText(false);
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
            else if (button == mainview.getLaysView().getButtonLaysRename() && mainview.getTabs().getSelectedIndex() != -1) {
                String newName = (new JOptionPane()).showInputDialog(null, "Enter new layer name", "Change layer name - Project CG & GPI", JOptionPane.QUESTION_MESSAGE);
                if (newName != null) {
                    ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    LayerPanel layer = image.getLayers().get(image.getIndexSelect());
                    String prevName = layer.getFileName();
                    layer.setFileName(newName);
                    this.updateLayers();
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Rename "+ prevName +" to "+ newName,image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }

            //Color balance view

            else if (button == mainview.getColorBalanceView().getButtonBalanceCancel()) {
                mainview.getColorBalanceView().setVisible(false);
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                image.setHasBalance(false);
                image.repaint();
            }
            else if (button == mainview.getBrightView().getBrightCancel()) {
                mainview.getBrightView().setVisible(false);
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                image.setHasBright(false);
                image.repaint();
            }
            else if (button == mainview.getColorBalanceView().getButtonBalanceExec()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                LayerPanel layer = image.getLayers().get(image.getIndexSelect());
                BufferedImage layerBuffer = layer.getImage();
                BufferedImage imageColor = image.getImgCol();
                for (int i = 0; i < layerBuffer.getHeight(); i++) {
                    for (int j = 0; j < layerBuffer.getWidth(); j++) {
                        layerBuffer.setRGB(j, i, imageColor.getRGB(j, i));
                    }
                }
                mainview.getColorBalanceView().setVisible(false);
                image.setHasBalance(false);
                this.updateLayers();
                image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Balance color on "+ layer.getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                this.updateHist();
                mainview.getTabs().grabFocus();
            }
            else if (button == mainview.getBrightView().getBrightExec()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                LayerPanel layer = image.getLayers().get(image.getIndexSelect());
                BufferedImage layerBuffer = layer.getImage();
                BufferedImage imageColor = image.getImgCol();
                for (int i = 0; i < layerBuffer.getHeight(); i++) {
                    for (int j = 0; j < layerBuffer.getWidth(); j++) {
                        layerBuffer.setRGB(j, i, imageColor.getRGB(j, i));
                    }
                }
                mainview.getBrightView().setVisible(false);
                image.setHasBright(false);
                this.updateLayers();
                image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Brightness Contrast on "+ layer.getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                this.updateHist();
                mainview.getTabs().grabFocus();
            }
        }
        else if (e.getSource().getClass() == JToggleButton.class) {
            JToggleButton button = (JToggleButton)e.getSource();
            if (button == mainview.getLaysView().getButtonLaysView()) {
                if (mainview.getTabs().getSelectedIndex() != -1) {
                    ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    LayerPanel layer = image.getLayers().get(image.getIndexSelect());
                    if (layer.getIsShowed())
                        layer.setIsShowed(false);
                    else
                        layer.setIsShowed(true);
                    this.updateLayers();
                }
                else {
                    button.setSelected(false);
                }
            }
            if (button == mainview.getToolsView().getToggleButtonToolText()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    if (mainview.getTabs().getSelectedIndex() != -1) {
                        for (ImagePanel image : mainview.getProjects())
                            image.setHasText(false);
                        mainview.getTextView().setVisible(false);
                        mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
                    }
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                }
            }
            else if (button == mainview.getToolsView().getToggleButtonToolMove()) {
                if (button == toggleButton) {
                    toggleButton = null;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
            else if (button == mainview.getToolsView().getToggleButtonToolDraw()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasDraw = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasDraw = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
            else if (button == mainview.getToolsView().getToggleButtonToolPaint()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasPaint = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasPaint = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
            else if (button == mainview.getToolsView().getToggleButtonToolClear()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasClear = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasClear = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
            else if (button == mainview.getToolsView().getToggleButtonToolRectangle()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasRect = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasRect = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }


            else if (button == mainview.getToolsView().getToggleButtonToolOval()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasOval = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasOval = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }



            else if (button == mainview.getToolsView().getToggleButtonToolStar()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasStar = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasStar = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }

            else if (button == mainview.getToolsView().getToggleButtonToolHexagon()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasHexagon = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasHexagon = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }

            else if (button == mainview.getToolsView().getToggleButtonToolParallelogram()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasParallelogram = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasParallelogram = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }

            else if (button == mainview.getToolsView().getToggleButtonToolPentagon()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasPentagon = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasPentagon = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }

            else if (button == mainview.getToolsView().getToggleButtonToolTrapezoid()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasTrapezoid = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasTrapezoid = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }

            else if (button == mainview.getToolsView().getToggleButtonToolTriangle()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasTriangle = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasTriangle = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }

            else if (button == mainview.getToolsView().getToggleButtonToolHearth()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasHearth = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasHearth = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
            //#TODO add more primitives


            else if (button == mainview.getToolsView().getToggleButtonToolCircle3()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasCircle3 = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasCircle3 = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }



            else if (button == mainview.getToolsView().getToggleButtonToolLine()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasLine = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasLine = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
            else if (button == mainview.getToolsView().getToggleButtonToolFill()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasFill = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasFill = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
            else if (button == mainview.getToolsView().getToggleButtonToolCut()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasCut = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasCut = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
            else if (button == mainview.getToolsView().getToggleButtonToolSelect()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    if (mainview.getTabs().getSelectedIndex() != -1) {
                        for (ImagePanel image : mainview.getProjects())
                            image.setHasSelect(false);
                    }
                    hasSelect = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasSelect = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
            else if (button == mainview.getToolsView().getToggleButtonToolPipette()) {
                if (button == toggleButton) {
                    toggleButton = null;
                    hasPipette = false;
                }
                else {
                    if (toggleButton != null)
                        toggleButton.doClick();
                    toggleButton = button;
                    hasPipette = true;
                }
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }

        }
        else if (e.getSource().getClass() == JComboBox.class) {
            JComboBox cb = (JComboBox)e.getSource();
            if (cb == mainview.getTextView().getComboBoxFont() || cb == mainview.getTextView().getComboBoxFontStyle()) {
                this.updateFont();
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
        }
    }

    private void openProject() throws IOException, ClassNotFoundException {
        if (mainview.getTabs().getSelectedIndex() != -1)
            fileChooser.setCurrentDirectory(new File(mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).getFilePath()));
        int returnVal = fileChooser.showOpenDialog(mainview);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //#TODO edit
            if (file.getName().toLowerCase().endsWith(".proj")) {
                ImagePanel image = new ImagePanel(this, new CareTaker());
                ImagePanel imagePanel;
                String state;
                int[] iconPix;
                JScrollPane scrollPane = new JScrollPane();
                Panels panel = new Panels(image);
                panel.addMouseListener(this);
                panel.addMouseWheelListener(this);
                panel.setLayout(new GridBagLayout());
                panel.add(image);
                mainview.getProjects().add(image);
                scrollPane.setViewportView(panel);
                ObjectInputStream objectInputStream =  new ObjectInputStream(new FileInputStream(file));
                image.setFileName((String) objectInputStream.readObject());
                image.setFilePath((String) objectInputStream.readObject());
                image.setZoom((float) objectInputStream.readObject());
                image.resetDims((int) objectInputStream.readObject(), (int) objectInputStream.readObject());
                image.setNbLayers((int) objectInputStream.readObject());
                image.setIndexSelect((int) objectInputStream.readObject());
                image.setLayers((ArrayList<LayerPanel>) objectInputStream.readObject());
                int nbMoments = (int) objectInputStream.readObject();
                for (int i = 0; i < nbMoments; i++) {
                    state = (String) objectInputStream.readObject();
                    iconPix = (int[]) objectInputStream.readObject();
                    imagePanel = new ImagePanel(this, image.getCareTaker());
                    imagePanel.setFileName((String) objectInputStream.readObject());
                    imagePanel.setFilePath((String) objectInputStream.readObject());
                    imagePanel.setZoom((float) objectInputStream.readObject());
                    imagePanel.resetDims((int) objectInputStream.readObject(), (int) objectInputStream.readObject());
                    imagePanel.setNbLayers((int) objectInputStream.readObject());
                    imagePanel.setIndexSelect((int) objectInputStream.readObject());
                    imagePanel.setLayers((ArrayList<LayerPanel>) objectInputStream.readObject());
                    image.getCareTaker().addMemento(new Moments(state, imagePanel, iconPix));
                }
                objectInputStream.close();
                image.buildLayersImg();
                mainview.addClosableTab(mainview.getTabs(), scrollPane, panel, file.getName(), MainView.CLOSE_TAB_ICON1);
            }
            else {
                ImagePanel image = new ImagePanel(file, this);
                image.addMouseListener(this);
                image.addMouseMotionListener(this);
                image.addMouseWheelListener(this);
                JScrollPane sp = new JScrollPane();
                Panels pan = new Panels(image);
                pan.addMouseListener(this);
                pan.addMouseWheelListener(this);
                pan.setLayout(new GridBagLayout());
                pan.add(image);
                mainview.getProjects().add(image);
                sp.setViewportView(pan);
                mainview.addClosableTab(mainview.getTabs(), sp, pan, file.getName(), MainView.CLOSE_TAB_ICON1);
                image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Open image: "+file.getName(),image.getImagePanelCopy(),image.getBuiltImage()));
                this.updateHist();
            }
            mainview.getTabs().grabFocus();

            //This is where a real application would open the file.
            System.out.println("Opening: " + file.getAbsolutePath() + ".");
        } else {
            System.out.println("Open command cancelled by user.");
        }
    }

    private void openLayer() {
        if (mainview.getTabs().getSelectedIndex() != -1)
            fileChooser.setCurrentDirectory(new File(mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).getFilePath()));
        int returnVal = fileChooser.showOpenDialog(mainview);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
            image.newLayer(file);
            mainview.getTabs().setSelectedIndex(mainview.getTabs().getSelectedIndex());
            this.updateLayers();
            mainview.updateWindow();
            image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("New layer: "+file.getName(),image.getImagePanelCopy(),image.getBuiltImage()));
            this.updateHist();
            mainview.getTabs().grabFocus();


            System.out.println("Opening: " + file.getAbsolutePath() + ".");
        } else {
            System.out.println("Open command cancelled by user.");
        }
    }



    public ConcurrentHashMap<String,Class> getClasses() {
        return model.getClasses();
    }

    public void setCol(Color c) {
        col = c;
        mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
    }

    public Color getCol() {
        return col;
    }

    public Font getTextFont() {
        return fontText;
    }

    public String getTextAdd() {
        return addText;
    }

    public void setTextAdd(String s) {
        addText = s;
    }

    public Creator getOriginator() {
        return originator;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().getClass() == TabbedPanes.class) {
            TabbedPanes tp = (TabbedPanes)e.getSource();
            if (tp == mainview.getTabs() && tp.getSelectedIndex() != -1) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                if (image.getThread() == null)

                mainview.getRotView().setVisible(false);
                if (image.getRotateProj())
                    mainview.getRotView().setTitle("Rotate Image");
                else
                    mainview.getRotView().setTitle("Rotate - " + image.getLayers().get(image.getIndexSelect()).getFileName());
                if (image.getHasRot()) {
                    mainview.getRotView().setVisible(true);
                    try {
                        mainview.getRotView().getSpinnerAngle().commitEdit();
                    } catch (ParseException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                mainview.getResizeView().setVisible(false);
                if (image.getResizeProj())
                    mainview.getResizeView().setTitle("Resize Image");
                else
                    mainview.getResizeView().setTitle("Resize - " + image.getLayers().get(image.getIndexSelect()).getFileName());
                if (image.getHasResize()) {
                    mainview.getResizeView().setVisible(true);
                }
                this.updateLayers();
                this.updateHist();
                mainview.getTabs().grabFocus();

                mainview.setTitle("Project CG & GPI - " + image.getFileName() + " (" + image.getImgW() + "x" + image.getImgH() + ")");
                mainview.setIconImage(image.getBuiltImage());
            }
            else {
                mainview.getSpinnerZoom().setValue(100);
            }
            mainview.updateWindow();
        }
        else if (e.getSource().getClass() == JSpinner.class) {
            JSpinner spin = (JSpinner)e.getSource();
            if (spin == mainview.getSpangle()) {
                model.sendNotification(e.getSource());
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).rotateSimule((float)spin.getValue());
            }
            else if (spin == mainview.getResizeView().getSpinnerResizeWidth()) {
                model.sendNotification(e.getSource());
            }
            else if (spin == mainview.getResizeView().getSpinnerResizeHeight()) {
                model.sendNotification(e.getSource());
            }
            else if (spin == mainview.getSpinnerZoom()) {
                if (mainview.getTabs().getSelectedIndex() != -1) {
                    ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    if (!(spin.getValue().equals(image.getZoom())))
                        model.zoomImg(spin, image, (float)spin.getValue());
                }
            }
            else if (spin == mainview.getTextView().getSpinnerFontSize()) {
                this.updateFont();
                mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
            }
        }
        else if (e.getSource().getClass() == JSlider.class) {
            JSlider slid = (JSlider)e.getSource();
            if (slid == mainview.getSliderAngle())
                model.sendNotification(e.getSource());
            else if (slid == mainview.getLaysView().getSliderLaysAlpha()) {
                int percent = slid.getValue();
                mainview.getLaysView().getLabelLaysAlpha().setText(String.valueOf(percent));
                if (mainview.getTabs().getSelectedIndex() != -1 && percent != prevp) {
                    ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    LayerPanel layer = image.getLayers().get(image.getIndexSelect());
                    BufferedImage layerBuffer = layer.getImage();
                    int argb, a, r, g, b;
                    for (int i = 0; i < layerBuffer.getWidth(); i++)
                        for (int j = 0; j < layerBuffer.getHeight(); j++) {
                            argb = layerBuffer.getRGB(i,j);
                            a = (argb >> 24) & 0xff;
                            r = (argb >> 16) & 0xff;
                            g = (argb >> 8) & 0xff;
                            b = (argb) & 0xff;
                            a = (int)Math.round((double)a*(double)100/(double) prevp);
                            if (a > 255)
                                a = 255;
                            if (a <= 0)
                                a = 1;
                            a = (int)Math.round((double)a*(double) percent /100);
                            if (a > 255)
                                a = 255;
                            if (a <= 0)
                                a = 1;
                            layerBuffer.setRGB(i, j, new Color(r,g,b,a).getRGB());
                        }
                    this.updateLayers();
                    mainview.updateWindow();
                    prevp = percent;
                }
            }
            else if (slid == mainview.getColorBalanceView().getSliderRed()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                BufferedImage layerBuffer = image.getLayers().get(image.getIndexSelect()).getImage();
                BufferedImage imageColor = image.getImgCol();
                int percent = slid.getValue();
                mainview.getColorBalanceView().getLabelRed().setText(String.valueOf(percent));
                if (mainview.getTabs().getSelectedIndex() != -1 && imageColor != null) {
                    int argb1 , argb2, a, r, g, b, nr;
                    for (int i = 0; i < layerBuffer.getWidth(); i++)
                        for (int j = 0; j < layerBuffer.getHeight(); j++) {
                            argb1 = layerBuffer.getRGB(i,j);
                            argb2 = imageColor.getRGB(i, j);
                            a = (argb2 >> 24) & 0xff;
                            r = (argb1 >> 16) & 0xff;
                            g = (argb2 >> 8) & 0xff;
                            b = (argb2) & 0xff;
                            nr = (int)Math.round((double)r*(double) percent /100);
                            nr = Math.min(nr, 255);
                            imageColor.setRGB(i, j, new Color(nr,g,b,a).getRGB());
                        }
                    image.repaint();
                }
            }
            else if (slid == mainview.getColorBalanceView().getSliderGreen()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                BufferedImage layerBuffer = image.getLayers().get(image.getIndexSelect()).getImage();
                BufferedImage imageColor = image.getImgCol();
                int percent = slid.getValue();
                mainview.getColorBalanceView().getLabelGreen().setText(String.valueOf(percent));
                if (mainview.getTabs().getSelectedIndex() != -1 && imageColor != null) {
                    int argb1, argb2, a, r, g, b, ng;
                    for (int i = 0; i < layerBuffer.getWidth(); i++)
                        for (int j = 0; j < layerBuffer.getHeight(); j++) {
                            argb1 = layerBuffer.getRGB(i,j);
                            argb2 = imageColor.getRGB(i, j);
                            a = (argb2 >> 24) & 0xff;
                            r = (argb2 >> 16) & 0xff;
                            g = (argb1 >> 8) & 0xff;
                            b = (argb2) & 0xff;
                            ng = (int)Math.round((double)g*(double) percent /100);
                            ng = Math.min(ng, 255);
                            imageColor.setRGB(i, j, new Color(r,ng,b,a).getRGB());
                        }
                    image.repaint();
                }
            }
            else if (slid == mainview.getColorBalanceView().getSliderBlue()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                BufferedImage layerBuffer = image.getLayers().get(image.getIndexSelect()).getImage();
                BufferedImage imageColor = image.getImgCol();
                int percent = slid.getValue();
                mainview.getColorBalanceView().getLabelBlue().setText(String.valueOf(percent));
                if (mainview.getTabs().getSelectedIndex() != -1 && imageColor != null) {
                    int argb1, argb2, a, r, g, b, nb;
                    for (int i = 0; i < layerBuffer.getWidth(); i++)
                        for (int j = 0; j < layerBuffer.getHeight(); j++) {
                            argb1 = layerBuffer.getRGB(i,j);
                            argb2 = imageColor.getRGB(i, j);
                            a = (argb2 >> 24) & 0xff;
                            r = (argb2 >> 16) & 0xff;
                            g = (argb2 >> 8) & 0xff;
                            b = (argb1) & 0xff;
                            nb = (int)Math.round((double)b*(double) percent /100);
                            nb = Math.min(nb, 255);
                            imageColor.setRGB(i, j, new Color(r,g,nb,a).getRGB());
                        }
                    image.repaint();
                }
            }
            else if (slid == mainview.getColorBalanceView().getSliderAlpha()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                BufferedImage layerBuf = image.getLayers().get(image.getIndexSelect()).getImage();
                BufferedImage imageColor = image.getImgCol();
                int percent = slid.getValue();
                mainview.getColorBalanceView().getLabelAlpha().setText(String.valueOf(percent));
                if (mainview.getTabs().getSelectedIndex() != -1 && imageColor != null) {
                    int argb1, argb2, a, r, g, b, na;
                    for (int i = 0; i < layerBuf.getWidth(); i++)
                        for (int j = 0; j < layerBuf.getHeight(); j++) {
                            argb1 = layerBuf.getRGB(i,j);
                            argb2 = imageColor.getRGB(i, j);
                            a = (argb1 >> 24) & 0xff;
                            r = (argb2 >> 16) & 0xff;
                            g = (argb2 >> 8) & 0xff;
                            b = (argb2) & 0xff;
                            na = (int)Math.round((double)a*(double) percent /100);
                            na = Math.min(na, 255);
                            imageColor.setRGB(i, j, new Color(r,g,b,na).getRGB());
                        }
                    image.repaint();
                }
            }
            else if (slid == mainview.getBrightView().getSliderBright()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                BufferedImage layerBuffer = image.getLayers().get(image.getIndexSelect()).getImage();
                BufferedImage imageColor = image.getImgCol();
                int percent = slid.getValue();
                mainview.getBrightView().getLabelBright().setText(String.valueOf(percent));
                if (mainview.getTabs().getSelectedIndex() != -1 && imageColor != null) {
                    int argb, a, r, g, b, nr, ng, nb;
                    for (int i = 0; i < layerBuffer.getWidth(); i++)
                        for (int j = 0; j < layerBuffer.getHeight(); j++) {
                            argb = layerBuffer.getRGB(i,j);
                            a = (argb >> 24) & 0xff;
                            r = (argb >> 16) & 0xff;
                            g = (argb >> 8) & 0xff;
                            b = (argb) & 0xff;
                            nr = (int)Math.round((double)r*(double) percent /100);
                            nr = Math.min(nr, 255);
                            ng = (int)Math.round((double)g*(double) percent /100);
                            ng = Math.min(ng, 255);
                            nb = (int)Math.round((double)b*(double) percent /100);
                            nb = Math.min(nb, 255);
                            imageColor.setRGB(i, j, new Color(nr,ng,nb,a).getRGB());
                        }
                    image.repaint();
                }
            }
            else if (slid == mainview.getBrightView().getSliderContrast()) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                BufferedImage layerBuffer = image.getLayers().get(image.getIndexSelect()).getImage();
                BufferedImage imageColor = image.getImgCol();
                int percent = slid.getValue();
                mainview.getBrightView().getLabelContrast().setText(String.valueOf(percent));
                if (mainview.getTabs().getSelectedIndex() != -1 && imageColor != null) {
                    int argb, a, r, g, b, nr, ng, nb;
                    for (int i = 0; i < layerBuffer.getWidth(); i++)
                        for (int j = 0; j < layerBuffer.getHeight(); j++) {
                            argb = layerBuffer.getRGB(i,j);
                            a = (argb >> 24) & 0xff;
                            r = (argb >> 16) & 0xff;
                            g = (argb >> 8) & 0xff;
                            b = (argb) & 0xff;
                            nr = (int)Math.round((double)128+(double)(r-128)*(double) percent /100);
                            nr = Math.min(nr, 255);
                            nr = Math.max(nr, 0);
                            ng = (int)Math.round((double)128+(double)(g-128)*(double) percent /100);
                            ng = Math.min(ng, 255);
                            ng = Math.max(ng, 0);
                            nb = (int)Math.round((double)128+(double)(b-128)*(double) percent /100);
                            nb = Math.min(nb, 255);
                            nb = Math.max(nb, 0);
                            imageColor.setRGB(i, j, new Color(nr,ng,nb,a).getRGB());
                        }
                    image.repaint();
                }
            }
        }
    }

    public void updateFont() {
        String name = (String)mainview.getTextView().getComboBoxFont().getSelectedItem();
        int style;
        int index = mainview.getTextView().getComboBoxFontStyle().getSelectedIndex();
        if (index == 0)
            style = Font.PLAIN;
        else if (index == 1)
            style = Font.BOLD;
        else if (index == 2)
            style = Font.ITALIC;
        else
            style = Font.BOLD | Font.ITALIC;
        int size = (int)mainview.getTextView().getSpinnerFontSize().getValue();
        fontText = new Font(name,style,size);
    }

    public void updateLayers() {
        ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
        DefaultListModel dlm = mainview.getLaysView().getModelLayers();
        dlm.removeAllElements();
        for (LayerPanel lp : image.getLayers()) {
            JLabel label = new JLabel(lp.getFileName()+" ("+lp.getImage().getWidth()+"x"+lp.getImage().getHeight()+")");
            label.setIcon(new ImageIcon(lp.getLayIcon()));
            dlm.addElement(label);
        }
        mainview.getLaysView().getListLayers().setSelectedIndex(image.getIndexSelect());
        LayerPanel layer = image.getLayers().get(image.getIndexSelect());
        if (layer.getIsShowed())
            mainview.getLaysView().getButtonLaysView().setSelected(false);
        else
            mainview.getLaysView().getButtonLaysView().setSelected(true);
        image.repaint();
    }

    public void updateHist() {
        ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
        DefaultListModel defaultListModel = mainview.getLaysView().getModelHistory();
        defaultListModel.removeAllElements();
        for (Moments moments : image.getCareTaker().getStates()) {
            JLabel label = new JLabel(moments.getSavedState());
            label.setIcon(new ImageIcon(moments.getIcon()));
            defaultListModel.addElement(label);
        }
        mainview.getSpinnerZoom().setValue((image.getZoom()));
        image.updateZoomImg();
        mainview.getLaysView().getListHistory().setSelectedIndex(defaultListModel.getSize()-1);
        image.repaint();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource() == mainview.getRotView()) {
            mainview.getRotView().getButtonCancel().doClick();
        }
        else if (e.getSource() == mainview.getTextView()) {
            mainview.getTextView().getButtonFontCancel().doClick();
        }
        else if (e.getSource() == mainview.getColorBalanceView()) {
            mainview.getColorBalanceView().getButtonBalanceCancel().doClick();
        }
        else if (e.getSource() == mainview.getBrightView()) {
            mainview.getBrightView().getBrightCancel().doClick();
        }
        else if (e.getSource() == mainview.getNewView()) {
            mainview.getNewView().setVisible(false);
        }
        else if (e.getSource() == mainview.getResizeView()) {
            ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
            image.setHasResize(false);
            mainview.getResizeView().setVisible(false);
        }
        else if (e.getSource() == mainview) {
            int option = (new JOptionPane()).showConfirmDialog(null, "Are you sure you want to quit the application?" + System.lineSeparator() + "Be sure to save all opened projects before leaving.", "Quit Project CG & GPI", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION)
                System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == mainview.getTabs() || e.getSource().getClass() == Panels.class || e.getSource().getClass() == ImagePanel.class) {
            mainview.getTabs().grabFocus();
        }
        if (e.getSource().getClass() == ImagePanel.class) {
            if (toggleButton != null) {
                if (toggleButton == mainview.getToolsView().getToggleButtonToolText()) {
                    ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                    image.setHasText(true);
                    image.setTextX(e.getX());
                    image.setTextY(e.getY());
                    mainview.getTextView().getTextFieldFontText().setText("");
                    mainview.getTextView().setVisible(true);
                }
                else {

                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().getClass() == ImagePanel.class) {
            ImagePanel image = (ImagePanel)e.getSource();
            LayerPanel layer = image.getLayers().get(image.getIndexSelect());
            image.setInitX(e.getX());
            image.setInitY(e.getY());
            image.setCursorX(e.getX());
            image.setCursorY(e.getY());
            if (toggleButton == mainview.getToolsView().getToggleButtonToolMove()) {
                layer.resetDim();
                image.setHasMove(true);
            }
            else if (hasDraw) {
                BufferedImage bufferedImage = layer.getImage();
                int size = mainview.getToolsView().getSliderToolSize().getValue();
                Graphics2D graphics2D = bufferedImage.createGraphics();
                graphics2D.setColor(col);
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics2D.fillRect((int)((float)e.getX()*100/image.getZoom())-size/2- layer.getLocalX()-1, (int)((float)e.getY()*100/image.getZoom())-size/2- layer.getLocalY()-1, size, size);
                image.repaint();
            }
            else if (hasPaint) {
                BufferedImage bufferedImage = layer.getImage();
                int size = mainview.getToolsView().getSliderToolSize().getValue();
                Graphics2D graphics2D = bufferedImage.createGraphics();
                graphics2D.setColor(col);
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics2D.fillOval((int)((float)e.getX()*100/image.getZoom())-size/2- layer.getLocalX()-1, (int)((float)e.getY()*100/image.getZoom())-size/2- layer.getLocalY()-1, size, size);
                image.repaint();
            }
            else if (hasClear) {
                BufferedImage bufferedImage = layer.getImage();
                int size = mainview.getToolsView().getSliderToolSize().getValue();
                int argb = new Color(255,255,255,0).getRGB();
                int initX = (int)((float)e.getX()*100/image.getZoom())-size/2- layer.getLocalX();
                int initY = (int)((float)e.getY()*100/image.getZoom())-size/2- layer.getLocalY();
                for (int i = initY; i < initY +size; i++) {
                    for (int j = initX; j < initX +size; j++) {
                        if (j >= 0 && j <= bufferedImage.getWidth()-1 &&
                                i >= 0 && i <= bufferedImage.getHeight()-1) {
                            bufferedImage.setRGB(j, i, argb);
                        }
                    }
                }
                image.repaint();
            }
            else if (hasRect) {
                image.setHasRect(true);
            }



            else if (hasOval) {
                image.setHasOval(true);
            }

            else if (hasStar) {
                image.setHasStar(true);
            }

            else if (hasHexagon) {
                image.setHasHexagon(true);
            }
            else if (hasParallelogram) {
                image.setHasParallelogram(true);
            }
            else if (hasPentagon) {
                image.setHasPentagon(true);
            }
            else if (hasTrapezoid) {
                image.setHasTrapezoid(true);
            }
            else if (hasTriangle) {
                image.setHasTriangle(true);
            }
            else if (hasHearth) {
                image.setHasHearth(true);
            }

            else if (hasCircle3) {
                image.setHasHearth(true);
            }

            //#TODO 3 add setHasShape -> and add in ->Image panel

            else if (hasLine) {
                image.setHasLine(true);
            }
            else if (hasFill) {
                int fillX = (int)((float)e.getX()*100/image.getZoom());
                int fillY = (int)((float)e.getY()*100/image.getZoom());
                if (fillX >= layer.getLocalX() && fillX <= layer.getLocalX()+ layer.getImage().getWidth() &&
                        fillY >= layer.getLocalY() && fillY <= layer.getLocalY()+ layer.getImage().getHeight()) {
                    model.fillDetect(layer.getImage(), fillX - layer.getLocalX(), fillY - layer.getLocalY(), layer.getImage().getRGB(fillX - layer.getLocalX(), fillY - layer.getLocalY()), col.getRGB());
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Fill "+ layer.getFileName()+" with color detect",image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }
            else if (hasCut) {
                image.setHasCut(true);
            }
            else if (hasSelect) {
                image.setHasSelect(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().getClass() == ImagePanel.class) {
            ImagePanel image = (ImagePanel)e.getSource();
            LayerPanel layer = image.getLayers().get(image.getIndexSelect());
            if (toggleButton == mainview.getToolsView().getToggleButtonToolMove()) {
                image.setHasMove(false);
                if (image.getCursorX() != image.getInitX() || image.getCursorY() != image.getInitY()) {
                    layer.setLocalX(layer.getLocalX()+(int)((float)(image.getCursorX()-image.getInitX())*(float)100/image.getZoom()));
                    layer.setLocalY(layer.getLocalY()+(int)((float)(image.getCursorY()-image.getInitY())*(float)100/image.getZoom()));
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Move "+ layer.getFileName()+" to "+ layer.getLocalX()+","+ layer.getLocalY(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }
            else if (hasDraw) {
                image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                this.updateHist();
                mainview.getTabs().grabFocus();
            }
            else if (hasPaint) {
                image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Paint on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                this.updateHist();
                mainview.getTabs().grabFocus();
            }
            else if (hasClear) {
                image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Clear on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                this.updateHist();
                mainview.getTabs().grabFocus();
            }

            else if (hasRect) {
                image.setHasRect(false);
                int ix = image.getInitX();
                int fx = image.getCursorX();
                int iy = image.getInitY();
                int fy = image.getCursorY();
                int sx = Math.min(ix, fx);
                int sy = Math.min(iy, fy);
                int sw = Math.abs(ix-fx);
                int sh = Math.abs(iy-fy);
                if (ix != fx && iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                    int x = (int)((float)sx*100/image.getZoom())- layer.getLocalX()-1;
                    int y = (int)((float)sy*100/image.getZoom())- layer.getLocalY()-1;

                    graphics2D.fillRect(x, y, (int)((float)sw*100/image.getZoom()), (int)((float)sh*100/image.getZoom()));

                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw rect on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }




            else if (hasOval) {
                image.setHasOval(false);
                int ix = image.getInitX();
                int fx = image.getCursorX();
                int iy = image.getInitY();
                int fy = image.getCursorY();
                int sx = Math.min(ix, fx);
                int sy = Math.min(iy, fy);
                int sw = Math.abs(ix-fx);
                int sh = Math.abs(iy-fy);
                if (ix != fx && iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    int x = (int)((float)sx*100/image.getZoom())- layer.getLocalX()-1;
                    int y = (int)((float)sy*100/image.getZoom())- layer.getLocalY()-1;

                    graphics2D.fillOval(x, y, (int)((float)sw*100/image.getZoom()), (int)((float)sh*100/image.getZoom()));

                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw oval on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }




            else if (hasStar) {
                image.setHasStar(false);
                int ix = image.getInitX();
                int fx = image.getCursorX();
                int iy = image.getInitY();
                int fy = image.getCursorY();
                int sx = Math.min(ix, fx);
                int sy = Math.min(iy, fy);
                int sw = Math.abs(ix-fx);
                int sh = Math.abs(iy-fy);
                if (ix != fx && iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    int x = (int)((float)sx*100/image.getZoom())- layer.getLocalX()-1;
                    int y = (int)((float)sy*100/image.getZoom())- layer.getLocalY()-1;

                    graphics2D.fill(createDefStar(x, y, (int)((float)sw*100/image.getZoom()), (int)((float)sh*100/image.getZoom())));

                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw star on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }


            else if (hasHexagon) {
                image.setHasHexagon(false);
                int ix = image.getInitX();
                int fx = image.getCursorX();
                int iy = image.getInitY();
                int fy = image.getCursorY();
                int sx = Math.min(ix, fx);
                int sy = Math.min(iy, fy);
                int sw = Math.abs(ix-fx);
                int sh = Math.abs(iy-fy);
                if (ix != fx && iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                    int radius = Math.abs((int)((float)sw*100/image.getZoom())-(int)((float)sh*100/image.getZoom()));
                    int x = (int)((float)sx*100/image.getZoom())- layer.getLocalX()-1;
                    int y = (int)((float)sy*100/image.getZoom())- layer.getLocalY()-1;

                    Hexagon hexagon = new Hexagon( new Point(x, y), radius);


                    graphics2D.fill(hexagon.getHexagon());
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw hexagon on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }


            else if (hasParallelogram) {
                image.setHasParallelogram(false);
                int ix = image.getInitX();
                int fx = image.getCursorX();
                int iy = image.getInitY();
                int fy = image.getCursorY();
                int sx = Math.min(ix, fx);
                int sy = Math.min(iy, fy);
                int sw = Math.abs(ix-fx);
                int sh = Math.abs(iy-fy);
                if (ix != fx && iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                    int x = (int)((float)sx*100/image.getZoom())- layer.getLocalX()-1;
                    int y = (int)((float)sy*100/image.getZoom())- layer.getLocalY()-1;


                    Parallelogram parallelogram = new Parallelogram( x, y,sw, sh,0,col,graphics2D);


                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw parallelogram on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }




            else if (hasPentagon) {
                image.setHasPentagon(false);
                int ix = image.getInitX();
                int fx = image.getCursorX();
                int iy = image.getInitY();
                int fy = image.getCursorY();
                int sx = Math.min(ix, fx);
                int sy = Math.min(iy, fy);
                int sw = Math.abs(ix-fx);
                int sh = Math.abs(iy-fy);
                if (ix != fx && iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                    int radius = Math.abs((int)((float)sw*100/image.getZoom())-(int)((float)sh*100/image.getZoom()));
                    int x = (int)((float)sx*100/image.getZoom())- layer.getLocalX()-1;
                    int y = (int)((float)sy*100/image.getZoom())- layer.getLocalY()-1;

                    Pentagon pentagon = new Pentagon( new Point(x, y), radius);

                    graphics2D.rotate(Math.toRadians( 54 ),x,y);

                    graphics2D.fill(pentagon.getPentagon());
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw pentagon on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }

            else if (hasTrapezoid) {
                image.setHasTrapezoid(false);
                int ix = image.getInitX();
                int fx = image.getCursorX();
                int iy = image.getInitY();
                int fy = image.getCursorY();
                int sx = Math.min(ix, fx);
                int sy = Math.min(iy, fy);
                int sw = Math.abs(ix-fx);
                int sh = Math.abs(iy-fy);
                if (ix != fx && iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    int x = (int)((float)sx*100/image.getZoom())- layer.getLocalX()-1;
                    int y = (int)((float)sy*100/image.getZoom())- layer.getLocalY()-1;

                    Trapezoid trapezoid = new Trapezoid(x, y,sw,sh,col,graphics2D);

                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw trapezoid on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }

            else if (hasTriangle) {
                image.setHasTriangle(false);
                int ix = image.getInitX();
                int fx = image.getCursorX();
                int iy = image.getInitY();
                int fy = image.getCursorY();
                int sx = Math.min(ix, fx);
                int sy = Math.min(iy, fy);
                int sw = Math.abs(ix-fx);
                int sh = Math.abs(iy-fy);
                if (ix != fx && iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();

                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);


                    int radius = Math.abs((int)((float)sw*100/image.getZoom())-(int)((float)sh*100/image.getZoom()));
                    int x = (int)((float)sx*100/image.getZoom())- layer.getLocalX()-1;
                    int y = (int)((float)sy*100/image.getZoom())- layer.getLocalY()-1;

                    Triangle triangle = new Triangle( new Point(x,y), radius);

                    graphics2D.rotate(Math.toRadians( 30 ),x,y);

                    graphics2D.fill(triangle.getTriangle());

                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw triangle on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }


            else if (hasHearth) {
                image.setHasHearth(false);
                int ix = image.getInitX();
                int fx = image.getCursorX();
                int iy = image.getInitY();
                int fy = image.getCursorY();
                int sx = Math.min(ix, fx);
                int sy = Math.min(iy, fy);
                int sw = Math.abs(ix-fx);
                int sh = Math.abs(iy-fy);
                if (ix != fx && iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                    int radius = Math.abs((int)((float)sw*100/image.getZoom())-(int)((float)sh*100/image.getZoom()));
                    int x = (int)((float)sx*100/image.getZoom())- layer.getLocalX()-1;
                    int y = (int)((float)sy*100/image.getZoom())- layer.getLocalY()-1;


                    Heart heart = new Heart();

                    graphics2D.fill(heart.Heart(x , y ,radius));
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw hearth on "+ image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }

            //#TODO 6 add here the thr formulas



            else if (hasCircle3) {
                image.setHasHearth(false);
                int ix = image.getInitX();
                int fx = image.getCursorX();
                int iy = image.getInitY();
                int fy = image.getCursorY();
                int sx = Math.min(ix, fx);
                int sy = Math.min(iy, fy);
                int sw = Math.abs(ix-fx);
                int sh = Math.abs(iy-fy);
                if (ix != fx && iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                    int radius = Math.abs((int)((float)sw*100/image.getZoom())-(int)((float)sh*100/image.getZoom()));
                    int x = (int)((float)sx*100/image.getZoom())- layer.getLocalX()-1;
                    int y = (int)((float)sy*100/image.getZoom())- layer.getLocalY()-1;



                    // #TODO FIX
                  //  Circle3 circle3 = new Circle3(x,y,sw,sh,col,graphics2D, radius);
                   System.out.println("TODO FIX");




                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw hearth on "+ image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }




            else if (hasLine) {
                image.setHasLine(false);
                int ix = (int)((float)image.getInitX()*100/image.getZoom())- layer.getLocalX();
                int fx = (int)((float)image.getCursorX()*100/image.getZoom())- layer.getLocalX();
                int iy = (int)((float)image.getInitY()*100/image.getZoom())- layer.getLocalY();
                int fy = (int)((float)image.getCursorY()*100/image.getZoom())- layer.getLocalY();
                if (ix != fx || iy != fy) {
                    Graphics2D graphics2D = layer.getImage().createGraphics();
                    graphics2D.setColor(col);
                    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                    graphics2D.drawLine(ix-1, iy-1, fx-1, fy-1);
                    image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Draw line on "+image.getLayers().get(image.getIndexSelect()).getFileName(),image.getImagePanelCopy(),image.getBuiltImage()));
                    this.updateHist();
                    mainview.getTabs().grabFocus();
                }
            }
            else if (hasCut) {
                image.setHasCut(false);
                image.cutImg();
                image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Cut image to "+image.getImgW()+"x"+image.getImgH(),image.getImagePanelCopy(),image.getBuiltImage()));
                this.updateHist();
                mainview.getTabs().grabFocus();
            }
            else if (hasSelect) {
                if (image.getInitX() == image.getCursorX() || image.getInitY() == image.getCursorY()) {
                    image.setHasSelect(false);
                    image.repaint();
                }
            }
            else if (hasPipette) {
                BufferedImage bufferedImage = image.getBuiltImage();
                int clickX = (int)((float)e.getX()*100/image.getZoom());
                int clickY = (int)((float)e.getY()*100/image.getZoom());
                int argb = bufferedImage.getRGB(clickX, clickY);
                col = new Color(argb,true);
                mainview.getToolsView().setColorChooser(col);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().getClass() == ImagePanel.class) {
            ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());

            if (toggleButton == mainview.getToolsView().getToggleButtonToolText())
                image.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            else if (toggleButton == mainview.getToolsView().getToggleButtonToolMove())
                image.setCursor(new Cursor(Cursor.MOVE_CURSOR));

                //#TODO 6 add here the hasShape

            else if (hasRect || hasOval || hasLine || hasCut || hasSelect || hasStar || hasHexagon || hasPentagon || hasTrapezoid || hasTriangle || hasHearth || hasCircle3  )
                image.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            else if (hasFill || hasPipette)
                image.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().getClass() == ImagePanel.class) {
            ((ImagePanel)e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (e.getSource() == mainview.getTabs()) {
            if (mainview.getTabs().getSelectedIndex() != -1) {
                boolean goodKey = false;
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                switch(keyCode) {
                    case KeyEvent.VK_UP:
                        image.getLayers().get(image.getIndexSelect()).decrLocalY();
                        goodKey = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        image.getLayers().get(image.getIndexSelect()).incrLocalY();
                        goodKey = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        image.getLayers().get(image.getIndexSelect()).decrLocalX();
                        goodKey = true;
                        break;
                    case KeyEvent.VK_RIGHT :
                        image.getLayers().get(image.getIndexSelect()).incrLocalX();
                        goodKey = true;
                        break;
                }
                if (goodKey) {
                    moving = true;
                    if (image.getHasRot() && !image.getRotateProj())
                        image.updateRotLoc();
                    image.repaint();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == mainview.getTextView().getTextFieldFontText()) {
            addText = mainview.getTextView().getTextFieldFontText().getText();
            mainview.getProjects().get(mainview.getTabs().getSelectedIndex()).repaint();
        }
        if (moving) {
            ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
            moving = false;
            image.getCareTaker().addMemento(mainview.getController().getOriginator().saveToMemento("Move "+image.getLayers().get(image.getIndexSelect()).getFileName()+" to "+image.getLayers().get(image.getIndexSelect()).getLocalX()+","+image.getLayers().get(image.getIndexSelect()).getLocalY(),image.getImagePanelCopy(),image.getBuiltImage()));
            this.updateHist();
            mainview.getTabs().grabFocus();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == mainview.getLaysView().getListLayers()) {
            if (mainview.getTabs().getSelectedIndex() != -1) {
                ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
                if (mainview.getLaysView().getListLayers().getSelectedIndex() != -1) {
                    image.setIndexSelect(mainview.getLaysView().getListLayers().getSelectedIndex());
                    LayerPanel lay = image.getLayers().get(image.getIndexSelect());
                    if (lay.getIsShowed())
                        mainview.getLaysView().getButtonLaysView().setSelected(false);
                    else
                        mainview.getLaysView().getButtonLaysView().setSelected(true);
                    image.updateZoomImg();
                    image.getParent().validate();
                    image.getParent().repaint();
                    image.repaint();
                }
            }
        }
        if (e.getSource() == mainview.getLaysView().getListHistory()) {
            if (mainview.getTabs().getSelectedIndex() != -1 && mainview.getLaysView().getModelHistory().size() > 1) {
                int tab = mainview.getTabs().getSelectedIndex();
                ImagePanel image = mainview.getProjects().get(tab);
                if (mainview.getLaysView().getListHistory().getSelectedIndex() != -1) {
                    Panels panel = (Panels) image.getParent();
                    panel.remove(image);
                    int index = mainview.getLaysView().getListHistory().getSelectedIndex();
                    originator.restoreFromMemento(image.getCareTaker().getMemento(index));
                    ImagePanel originatorImg = originator.getImage();
                    ImagePanel newImage = originatorImg.getImgSavedCopy();

                    newImage.addMouseMotionListener(this);
                    newImage.addMouseWheelListener(this);
                    mainview.getProjects().set(tab, newImage);
                    newImage.setZoom((float)mainview.getSpinnerZoom().getValue());
                    newImage.updateZoomImg();
                    panel.add(newImage);
                    panel.validate();
                    panel.repaint();
                    newImage.repaint();
                    this.updateLayers();
                    mainview.updateWindow();
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource().getClass() == ImagePanel.class) {
            ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
            mainview.getLabelX().setText("x: "+(int)((float)e.getX()*100/image.getZoom()-2));
            mainview.getLabelY().setText("y: "+(int)((float)e.getY()*100/image.getZoom()-2));
            image.setCursorX(e.getX());
            image.setCursorY(e.getY());
            if (image.getHasMove()) {
                image.repaint();
            }
            if (hasDraw) {
                LayerPanel layer = image.getLayers().get(image.getIndexSelect());
                BufferedImage bufferedImage = layer.getImage();
                int size = mainview.getToolsView().getSliderToolSize().getValue();
                Graphics2D graphics2D = bufferedImage.createGraphics();
                graphics2D.setColor(col);
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics2D.fillRect((int)((float)e.getX()*100/image.getZoom())-size/2- layer.getLocalX()-1, (int)((float)e.getY()*100/image.getZoom())-size/2- layer.getLocalY()-1, size, size);
                image.repaint();
            }
            else if (hasPaint) {
                LayerPanel layerPanel = image.getLayers().get(image.getIndexSelect());
                BufferedImage bufferedImage = layerPanel.getImage();
                int size = mainview.getToolsView().getSliderToolSize().getValue();
                Graphics2D graphics2D = bufferedImage.createGraphics();
                graphics2D.setColor(col);
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics2D.fillOval((int)((float)e.getX()*100/image.getZoom())-size/2- layerPanel.getLocalX()-1, (int)((float)e.getY()*100/image.getZoom())-size/2- layerPanel.getLocalY()-1, size, size);
                image.repaint();
            }
            else if (hasClear) {
                LayerPanel layer = image.getLayers().get(image.getIndexSelect());
                BufferedImage bufferedImage = layer.getImage();
                int size = mainview.getToolsView().getSliderToolSize().getValue();
                int argb = new Color(255,255,255,0).getRGB();
                int initX = (int)((float)e.getX()*100/image.getZoom())-size/2- layer.getLocalX();
                int initY = (int)((float)e.getY()*100/image.getZoom())-size/2- layer.getLocalY();
                for (int i = initY; i < initY +size; i++) {
                    for (int j = initX; j < initX +size; j++) {
                        if (j >= 0 && j <= bufferedImage.getWidth()-1 &&
                                i >= 0 && i <= bufferedImage.getHeight()-1) {
                            bufferedImage.setRGB(j, i, argb);
                        }
                    }
                }
                image.repaint();
            }
            else if (image.getHasRect() || image.getHasOval() || image.getHasLine() || image.getHasCut() || image.getHasSelect())
                image.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getSource().getClass() == ImagePanel.class) {
            ImagePanel image = mainview.getProjects().get(mainview.getTabs().getSelectedIndex());
            mainview.getLabelX().setText("x: "+(int)((float)e.getX()*100/image.getZoom()-2));
            mainview.getLabelY().setText("y: "+(int)((float)e.getY()*100/image.getZoom()-2));
            if (hasPaint || hasDraw || hasClear) {
                image.setCursorX(e.getX());
                image.setCursorY(e.getY());
                image.repaint();
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int up = 1;
        int down = 2;
        int count = e.getWheelRotation();
        int direction = (count < 0) ? up : down;
        if (e.getSource().getClass() == ImagePanel.class && mainview.getTabs().getSelectedIndex() != -1) {
            if (direction == up) {
                mainview.getSpinnerZoom().setValue((float)mainview.getSpinnerZoom().getValue()+1);
            }
            else {
                mainview.getSpinnerZoom().setValue((float)mainview.getSpinnerZoom().getValue()-1);
            }
        }
        else if (e.getSource().getClass() == Panels.class && mainview.getTabs().getSelectedIndex() != -1) {
            Panels myPanel = (Panels)e.getSource();
            JScrollPane scrollPane = (JScrollPane) myPanel.getParent().getParent();
            if (direction == up) {
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue()+10);
            }
            else {
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue()-10);
            }
        }
    }



}

