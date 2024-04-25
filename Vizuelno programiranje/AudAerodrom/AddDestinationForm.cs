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
    public partial class AddDestinationForm : Form {
        public Destination destinationToAdd { get; set; }
        public AddDestinationForm() {
            InitializeComponent();
        }

        private void label3_Click(object sender, EventArgs e) {

        }

        private void btnAdd_Click(object sender, EventArgs e) {
            destinationToAdd = new Destination(tbDestination.Text, int.Parse(nudDistance.Text), int.Parse(nudPrice.Text));
            this.DialogResult = DialogResult.OK;
        }

        private void btnCancel_Click(object sender, EventArgs e) {
            this.DialogResult = DialogResult.Cancel;
        }
    }
}
