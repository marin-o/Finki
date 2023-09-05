using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IspitniBuses {
    public partial class Form1 : Form {
        public Form1() {
            InitializeComponent();
        }

        private void btnAddBus_Click(object sender, EventArgs e) {
            AddBusForm form = new AddBusForm();
            if (form.ShowDialog() == DialogResult.OK) {
                Bus bus = form.Bus;
                lbBuses.Items.Add(bus);
            }
        }

        private void btnDelBus_Click(object sender, EventArgs e) {
            DialogResult d = MessageBox.Show("Sigurno?","Brisenje bus",MessageBoxButtons.YesNo,MessageBoxIcon.Question);
            if(d == DialogResult.Yes && lbBuses.Items.Count > 0 && lbBuses.SelectedIndex > -1) {
                lbBuses.Items.RemoveAt(lbBuses.SelectedIndex);
            }
        }

        private void btnAddLine_Click(object sender, EventArgs e) {
            AddLineForm f = new AddLineForm();
            DialogResult d = f.ShowDialog();
            if( d == DialogResult.OK && lbBuses.Items.Count > 0 && lbBuses.SelectedIndex > -1 ) {
                Bus b = lbBuses.SelectedItem as Bus;
                b.AddLine(f.Line);
            }
            lbBuses_SelectedIndexChanged(null, null);
        }

        private void CalculateLines() {
            if(lbLines.Items.Count > 0) {
                Line me = lbLines.Items[0] as Line;
                decimal avg = 0;
                foreach(Line line in lbLines.Items) {
                    if(line.Price>me.Price) {
                        me = line;
                    }
                    avg += line.Price;
                }
                tbMostExpensive.Text = me.ToString();
                tbAvgPrice.Text = (avg/lbLines.Items.Count).ToString();
            }
        }

        private void lbBuses_SelectedIndexChanged(object sender, EventArgs e) {
            if( lbBuses.SelectedIndex > -1 ) { 
                lbLines.Items.Clear();
                Bus b = lbBuses.SelectedItem as Bus;
                lbLines.Items.AddRange(b.Lines.ToArray<Line>());
                CalculateLines();
            } else {
                lbLines.Items.Clear();
            }
        }
    }
}
