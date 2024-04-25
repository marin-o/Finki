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
    public partial class AddLineForm : Form {
        public Line Line { get; set; }
        public AddLineForm() {
            InitializeComponent();
        }

        private void tbDest_Validating(object sender, CancelEventArgs e) {
            if(tbDest.Text == string.Empty ) {
                errorProvider1.SetError(tbDest, "Mora da ima destinacija");
                e.Cancel = true;
            }
            else {
                errorProvider1.SetError(tbDest, null);
                e.Cancel = false;
            }
        }

        private void btnSave_Click(object sender, EventArgs e) {
            if( ValidateChildren() ) {
                Line = new Line(tbDest.Text,(int)nudHour.Value,(int)nudMin.Value,nudPrice.Value);
                DialogResult = DialogResult.OK;
            }
        }

        private void btnCancel_Click(object sender, EventArgs e) {
            DialogResult = DialogResult.Cancel;
        }
    }
}
