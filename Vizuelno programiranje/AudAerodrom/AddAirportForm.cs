using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AudAerodrom {
    public partial class AddAirportForm : Form {
        public Airport airport { get; set; }
        public AddAirportForm() {
            InitializeComponent();
        }

        private void tbName_Validating(object sender, CancelEventArgs e) {
            if( tbName.Text == "" ) {
                errorProvider1.SetError(tbName, "Name must not be empty");
            }
        }

        private void tbCity_Validating(object sender, CancelEventArgs e) {
            if( tbCity.Text == "" ) {
                errorProvider1.SetError(tbCity, "City must not be empty");
            }
        }

        private void tbCode_Validating(object sender, CancelEventArgs e) {
            if(tbCode.Text.Length == 3 ) {
                bool AllUpperLetters = true;
                foreach(char c in tbCode.Text) {
                    if( !char.IsUpper(c) || !char.IsLetter(c)) {
                        AllUpperLetters = false;
                        break;
                    }
                }

                if( !AllUpperLetters ) {
                    errorProvider1.SetError(tbCode, "Code must be 3 upper letters");
                    e.Cancel = true;
                }
                else {
                    errorProvider1.SetError(tbCode, string.Empty);
                    e.Cancel = false;
                }
            } else {
                errorProvider1.SetError(tbCode, "Code length must be 3");
                e.Cancel = true;
            }
        }

        private void btnConfirm_Click(object sender, EventArgs e) {
            if( ValidateChildren() ) {
                airport = new Airport(tbCode.Text, tbName.Text, tbCity.Text);
                this.DialogResult = DialogResult.OK;
            }
        }

        private void btnCancel_Click(object sender, EventArgs e) {
            this.DialogResult = DialogResult.Cancel;
        }
    }
}
