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
    public partial class Form1 : Form {
        public Form1() {
            InitializeComponent();
        }

        private void btnAddAirport_Click(object sender, EventArgs e) {
            AddAirportForm addAirportForm = new AddAirportForm();
            
            if( addAirportForm.ShowDialog() == DialogResult.OK) {
                lbAirports.Items.Add(addAirportForm.airport);
            }
        }

        private void btnDeleteAirport_Click(object sender, EventArgs e) {
            if(lbAirports.SelectedIndex != -1 ) {
                DialogResult dg = MessageBox.Show("U sure?", "Sigurno?", MessageBoxButtons.YesNo,MessageBoxIcon.Warning);
                if(dg == DialogResult.Yes) {
                    lbAirports.Items.RemoveAt(lbAirports.SelectedIndex);
                }
            }
        }

        private void loadDestinationData() {
            lbDestinations.Items.Clear();
            tbAverageDestination.Text = "";
            tbMostExpensiveDestination.Text = "";
            Airport airport = lbAirports.SelectedItem as Airport;
            if(lbAirports.Items.Count > 0 && airport.Destinations.Count > 0 ) {
                lbDestinations.Items.Clear();
                int sum = 0;
                foreach( Destination destination in airport.Destinations ) {
                    lbDestinations.Items.Add(destination);
                    sum += destination.Distance;
                }
                tbAverageDestination.Text = ((double)sum/airport.Destinations.Count).ToString();
                tbMostExpensiveDestination.Text = airport.Destinations.Max(x => x.Price).ToString();
            }

        }

        private void btnAddDestination_Click(object sender, EventArgs e) {
            if(lbAirports.SelectedIndex != -1 ) {
                AddDestinationForm addDestinationForm = new AddDestinationForm();
                if(addDestinationForm.ShowDialog() == DialogResult.OK ) {
                    Airport airport = lbAirports.SelectedItem as Airport;
                    airport.Destinations.Add(addDestinationForm.destinationToAdd);
                    loadDestinationData();
                }
            }
        }

        private void lbAirports_SelectedIndexChanged(object sender, EventArgs e) {
            loadDestinationData();
        }
    }
}
