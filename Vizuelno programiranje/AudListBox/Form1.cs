using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AudListBox {
    public partial class Form1 : Form {
        public Form1() {
            InitializeComponent();
        }

        private void btnAddItem_Click(object sender, EventArgs e) {
            if(tbNewItem.Text != "") {
                foreach (string item in lbItems.Items ) {
                    if(item == tbNewItem.Text) {
                        tbNewItem.Text = "";
                        MessageBox.Show("Item already exists");
                        return;
                    }
                }
                lbItems.Items.Add(tbNewItem.Text);
                tbNewItem.Text = "";
            }
        }

        private void btnMoveItem_Click(object sender, EventArgs e) {
            if (lbItems.SelectedIndex != -1) {
                clbStrings.Items.Add(lbItems.SelectedItem as string);
                lbItems.Items.Remove(lbItems.SelectedItem);
            }
        }
    }
}
