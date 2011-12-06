package de.tudresden.inf.lat.uel.plugin.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;

/**
 * 
 * @author Julian Mendez
 */
public class JComboBoxOfLabelId extends JComboBox {

	private static final long serialVersionUID = -1589168297784841281L;

	private List<LabelId> list = new ArrayList<LabelId>();
	private boolean processing = false;

	public JComboBoxOfLabelId() {
		super();
		addActionListener(this);
		setEditable(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (!this.processing) {
			String lastText = event.getActionCommand();
			if (!lastText.equals(getActionCommand())) {
				int itemIndex = binarySearch(this.list, lastText);
				this.setSelectedIndex(itemIndex);
			}
		}
	}

	/**
	 * This method is not supported.
	 * 
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void addItem(Object anObject) {
		throw new UnsupportedOperationException();
	}

	private int binarySearch(List<LabelId> list, String origKey) {
		LabelId key = new LabelId(origKey, origKey);
		int left = 0;
		int right = list.size();
		int mid = left;
		while (left < right - 1) {
			mid = (left + right) / 2;
			LabelId current = list.get(mid);
			if (current.equals(key)) {
				left = mid;
				right = mid;
			} else if (current.compareTo(key) < 0) {
				left = mid;
			} else if (current.compareTo(key) > 0) {
				right = mid;
			}
		}
		return left;
	}

	public LabelId getSelectedElement() {
		return this.list.get(getSelectedIndex());
	}

	public void setItemList(List<LabelId> origList) {
		this.processing = true;
		this.list.clear();
		this.list.addAll(origList);
		Collections.sort(this.list);
		super.removeAllItems();
		for (LabelId label : this.list) {
			super.addItem(label.getLabel());
		}
		this.processing = false;
	}

}
