using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IspitniAvtomobili {
    public partial class NewMakeForm : Form {
        public Make Make { get; set; } = null;
        public NewMakeForm() {
            InitializeComponent();
        }

        private void btnAdd_Click(object sender, EventArgs e) {
            if(ValidateChildren()) {
                Make = new Make(tbName.Text, tbCode.Text);
                DialogResult = DialogResult.OK;
            }
        }

        private void btnCancel_Click(object sender, EventArgs e) {
            DialogResult = DialogResult.Cancel;
        }

        private void tbName_Validating(object sender, CancelEventArgs e) {
            if(tbName.Text.Length < 1 ) {
                epMakeForm.SetError(tbName,"Name must be filled");
                e.Cancel = true;
            } else {
                epMakeForm.SetError(tbName, null);
                e.Cancel = false;
            }
        }

        private void tbCode_Validating(object sender, CancelEventArgs e) {
            if(tbCode.Text.Length < 1 ) {
                epMakeForm.SetError(tbCode, "Code must be filled");
                e.Cancel = true;
            } else {
                epMakeForm.SetError(tbCode, null);
                e.Cancel = false;
            }
        }
    }
}
