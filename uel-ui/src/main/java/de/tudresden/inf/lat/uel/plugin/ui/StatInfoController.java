package de.tudresden.inf.lat.uel.plugin.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

/**
 * This is the controller of the panel that shows statistical information.
 * 
 * @author Julian Mendez
 */
class StatInfoController implements ActionListener {

	private static final String actionSaveButton = "save goal";

	private StatInfoView view = null;

	public StatInfoController(StatInfoView v) {
		this.view = v;
		init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e == null) {
			throw new IllegalArgumentException("Null argument.");
		}

		String cmd = e.getActionCommand();
		if (cmd.equals(actionSaveButton)) {
			executeSaveGoal();
		} else {
			throw new IllegalStateException();
		}
	}

	public void close() {
		getView().setVisible(false);
		getView().dispose();
	}

	private void executeSaveGoal() {
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showSaveDialog(getView());
		File file = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
		}
		if (file != null) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(getModel().printPluginGoal());
				writer.flush();
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public StatInfo getModel() {
		return getView().getModel();
	}

	public StatInfoView getView() {
		return this.view;
	}

	private void init() {
		getView().addSaveButtonListener(this, actionSaveButton);
	}

	public void open() {
		getView().update();
		getView().setVisible(true);
	}

	public void update() {
		getView().update();
	}

}