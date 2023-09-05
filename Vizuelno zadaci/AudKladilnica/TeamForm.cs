using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AudKladilnica {
    public partial class TeamForm : Form {

        public Team CreatedTeam{ get; set; }
        public TeamForm() {
            InitializeComponent();
        }

        private void TeamForm_Load(object sender, EventArgs e) {

        }

        private void btnOk_Click(object sender, EventArgs e) {
            if (tbName.Text != "" && tbCountry.Text != "" ) {
                CreatedTeam = new Team(tbName.Text, tbCountry.Text);
                this.DialogResult = DialogResult.OK;
            }
        }

        private void btnCancel_Click(object sender, EventArgs e) {
            this.DialogResult = DialogResult.Cancel;
        }
    }
}
