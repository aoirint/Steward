package com.kanomiya.steward.editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.Collator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.assets.resource.type.ResourceType;
import com.kanomiya.steward.model.script.IScriptOwner;
import com.kanomiya.steward.model.script.Script;
import com.kanomiya.steward.model.script.ScriptCode;
import com.kanomiya.steward.model.script.ScriptEventType;

/**
 * @author Kanomiya
 *
 */
public class PanelScriptOwner extends JPanel {

	public JComboBox inputScriptEventType;
	public JComboBox inputScriptSrc;
	public JTable inputArguments;


	public PanelScriptOwner(Game game, IScriptOwner owner, FrameEditor editor)
	{
		super();
		PanelScriptOwner thiz = this;

		{
			JPanel p = new JPanel();

			inputScriptEventType = new JComboBox();
			inputScriptEventType.setPreferredSize(new Dimension(150, 20));

			List<ScriptEventType> list = Lists.newArrayList(ScriptEventType.values());
			Collator collator = Collator.getInstance();
			collator.setStrength(Collator.PRIMARY);
			list.sort(new ResourceIdComparator(collator));

			Iterator<ScriptEventType> itr = list.iterator();
			while (itr.hasNext())
			{
				inputScriptEventType.addItem(itr.next().getId());
			}

			inputScriptEventType.addItemListener(new ItemListener()
			{
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED)
					{
						initScript(owner);
					}

				}
			});

			p.add(new JLabel(game.assets.translate(("scriptEventType"))));
			p.add(inputScriptEventType);


			JButton cReflect = new JButton(game.assets.translate("vocabulary.reflection"));
			cReflect.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {

					ScriptEventType type = ScriptEventType.getFromId((String) inputScriptEventType.getSelectedItem());

					if (0 <= inputScriptSrc.getSelectedIndex())
					{

						if (inputScriptSrc.getSelectedIndex() == 0) owner.setScript(type, null);
						else
						{
							Script script = new Script(((String) inputScriptSrc.getSelectedItem()));

							Map<String, String> arguments = null;
							DefaultTableModel model = (DefaultTableModel) inputArguments.getModel();

							for (int i=0; i<model.getRowCount(); i++)
							{
								String key = (String) model.getValueAt(i, 0);
								String value = (String) model.getValueAt(i, 1);

								if (key != null && value != null && ! key.equals("") && ! value.equals(""))
								{
									if (arguments == null) arguments = Maps.newHashMap();
									arguments.put(key, value);
								}
							}

							script.arguments = arguments;

							owner.setScript(type, script);
						}
					}

					editor.refreshTree();
					editor.tree.setSelectionPath(editor.getTreePathFromUserObject(editor.nodeRoot, owner, true));
					editor.splitPane.setRightComponent(thiz);
				}
			});
			p.add(cReflect);

			add(p);
		}

		{
			JPanel p = new JPanel();

			inputScriptSrc = new JComboBox();
			inputScriptSrc.setPreferredSize(new Dimension(150, 20));

			inputScriptSrc.addItem("(nothing)");

			List<ScriptCode> list = Lists.newArrayList(game.assets.registries.get(ResourceType.rtScriptCode).values());
			Collator collator = Collator.getInstance();
			collator.setStrength(Collator.PRIMARY);
			list.sort(new ResourceIdComparator(collator));

			Iterator<ScriptCode> itr = list.iterator();
			while (itr.hasNext())
			{
				inputScriptSrc.addItem(itr.next().id);
			}

			p.add(new JLabel(game.assets.translate(("vocabulary.scriptSource"))));
			p.add(inputScriptSrc);
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setPreferredSize(new Dimension(400, 20));
			p.add(new JLabel(game.assets.translate("vocabulary.argument")));

			JPanel p2 = new JPanel();

			JScrollPane tableSP = new JScrollPane();

			DefaultTableColumnModel headerModel = new DefaultTableColumnModel();

			TableColumn header_c1 = new TableColumn();
			header_c1.setHeaderValue(game.assets.translate("vocabulary.itemName"));
			headerModel.addColumn(header_c1);

			TableColumn header_c2 = new TableColumn();
			header_c2.setHeaderValue(game.assets.translate("vocabulary.value"));
			headerModel.addColumn(header_c2);

			DefaultTableModel argTable = new DefaultTableModel(0, 2);

			inputArguments = new JTable(argTable);
			inputArguments.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

			JTableHeader header = new JTableHeader(headerModel);

			inputArguments.setTableHeader(header);
			tableSP.setViewportView(inputArguments);
			tableSP.setPreferredSize(new Dimension(350, 200));

			p2.add(tableSP);

			JPanel p3 = new JPanel();

			JButton btnAddRow = new JButton(game.assets.translate("vocabulary.add"));
			btnAddRow.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					argTable.addRow(new String[2]);
				}
			});

			p3.add(btnAddRow);

			JButton btnDelRow = new JButton(game.assets.translate("vocabulary.delete"));
			btnDelRow.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = inputArguments.getSelectedRow();
					if (0 <= row) argTable.removeRow(row);
				}
			});

			p3.add(btnDelRow);

			add(p);
			add(p2);
			add(p3);
		}


		initScript(owner);


	}

	protected void initScript(IScriptOwner owner)
	{
		ScriptEventType type = ScriptEventType.getFromId((String) inputScriptEventType.getSelectedItem());

		inputScriptSrc.setSelectedIndex(0);
		DefaultTableModel model = (DefaultTableModel) inputArguments.getModel();
		while (0 < model.getRowCount()) model.removeRow(0);

		if (owner.hasScript(type))
		{
			Script script = owner.getScript(type);
			inputScriptSrc.setSelectedItem(script.src);

			if (script.hasArgument())
			{
				Iterator<String> argItr = script.arguments.keySet().iterator();

				while (argItr.hasNext())
				{
					String key = argItr.next();

					model.addRow(new String[] { key, script.arguments.get(key) });

				}

			}

		}




	}

}
