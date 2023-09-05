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
    public partial class AddBusForm : Form {
        public Bus Bus { get; set; }
        public AddBusForm() {
            InitializeComponent();
        }

        private void tbName_Validating(object sender, CancelEventArgs e) {
            if(tbName.Text == string.Empty ) {
                errorProvider1.SetError(tbName, "Name is required");
                e.Cancel = true;
            }
            else {
                errorProvider1.SetError(tbName, null);
                e.Cancel = false;
            }
        }

        private bool CheckReg() {
            string text = tbReg.Text;
            if( text == string.Empty || text.Length>4) {
                return false;
            }
            foreach(char c in text ) {
                if(!char.IsDigit(c)) {
                    return false;
                }
            }
            return true;
        }

        private void tbReg_Validating(object sender, CancelEventArgs e) {
            if(!CheckReg()) {
                errorProvider1.SetError(tbReg, "Mora da bide 4 cifri");
                e.Cancel= true;
            }
            else {
                errorProvider1.SetError(tbReg, null);
                e.Cancel = false;
            }
        }

        private void btnSave_Click(object sender, EventArgs e) {
            if( ValidateChildren() ) {
                    Bus = new Bus(tbReg.Text, tbName.Text, cbLocal.Checked);
                    DialogResult = DialogResult.OK;
            }
        }

        private void btnCancel_Click(object sender, EventArgs e) {
            DialogResult = DialogResult.Cancel;
        }
    }
}
