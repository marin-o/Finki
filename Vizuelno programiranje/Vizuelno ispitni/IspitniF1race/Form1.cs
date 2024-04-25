using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IspitniF1race {
    public partial class Form1 : Form {
        private decimal oldMinutes;
        public Form1() {
            InitializeComponent();
            oldMinutes = nudMinutes.Value;
        }

        private void nudMinutes_ValueChanged(object sender, EventArgs e) {
            if( nudMinutes.Value < oldMinutes && nudSeconds.Value == 0.0M )
                nudSeconds.Value = 59.0M;

        }

        private void nudSeconds_ValueChanged(object sender, EventArgs e) {
            if( nudSeconds.Value > 59.0M ) {
                nudSeconds.Value = 0.0M;
                nudMinutes.Value += 1.0M;
            }
        }

        private void btnAddDriver_Click(object sender, EventArgs e) {
            Form2 dlg = new Form2();
            if (dlg.ShowDialog() == DialogResult.OK ) {
                lbDrivers.Items.Add(dlg.NewDriver);
            }
        }

        private void lbDrivers_SelectedIndexChanged(object sender, EventArgs e) {
            if( lbDrivers.SelectedItem != null ) {
                lbLaps.Items.Clear();
                Driver d = lbDrivers.SelectedItem as Driver;
                foreach (Lap l in d.Laps ) {
                    if(l.getSeconds() <= (int)nudMinutes.Value)
                        lbLaps.Items.Add(l);
                }
            }
        }

        private void btnRemoveDriver_Click(object sender, EventArgs e) {
            if( lbDrivers.SelectedIndex != -1 ) {
                if(MessageBox.Show("Are you sure?","Delete driver",MessageBoxButtons.YesNo) == DialogResult.Yes)
                lbDrivers.Items.RemoveAt(lbDrivers.SelectedIndex);
                lbLaps.Items.Clear();
            }
        }

        private void btnAddLap_Click(object sender, EventArgs e) {
            if( lbDrivers.SelectedIndex != -1 ) {
                Lap l = new Lap((int)nudMinutes.Value, (int)nudSeconds.Value);
                Driver d = lbDrivers.SelectedItem as Driver;
                d.addLap(l);
                nudMinutes.Value = 0;
                nudSeconds.Value = 0;
                lbDrivers_SelectedIndexChanged(null, null);
            }
        }

        private void nudMinutes_MouseDown(object sender, MouseEventArgs e) {
            oldMinutes = nudMinutes.Value;
        }
    }
}
