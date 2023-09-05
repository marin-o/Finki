using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IspitniF1race {
    public partial class Form2 : Form {
        public Driver NewDriver { get; set; } = null;
        public Form2() {
            InitializeComponent();
        }

        private void tbName_Validating(object sender, CancelEventArgs e) {
            if(tbName.Text == "" ) {
                errorProvider1.SetError(tbName, "Внесете име!");
                e.Cancel = true;
            }
            else {
                errorProvider1.SetError(tbName, null);
                e.Cancel = false;
            }
        }

        private void btnAdd_Click(object sender, EventArgs e) {
            if( ValidateChildren() ) {
                NewDriver = new Driver(tbName.Text, (int)nudAge.Value);
                if(cbFirst.Checked)
                    NewDriver.IsFirst = true;
                DialogResult = DialogResult.OK;
            }
        }

        private void btnCancel_Click(object sender, EventArgs e) {
            if(errorProvider1.GetError(tbName) != null || errorProvider1.GetError(tbName) == null)
                DialogResult = DialogResult.Cancel;
        }
    }
}
