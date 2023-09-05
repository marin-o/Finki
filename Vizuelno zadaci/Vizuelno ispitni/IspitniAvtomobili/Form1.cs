using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IspitniAvtomobili {
    public partial class Form1 : Form {
        public Form1() {
            InitializeComponent();
        }

        private void btnAddCar_Click(object sender, EventArgs e) {
            if(cbMarka.Items.Count == 0) {
                MessageBox.Show("Dodajte marka!");
            }
            else if(cbMarka.SelectedIndex == -1 ) {
                MessageBox.Show("Selektirajte marka!");
            }
            else if(tbModel.Text.Length < 1 ) {
                MessageBox.Show("Napisete model!");
            }
            else {
                Car car = new Car(cbMarka.SelectedItem as Make, tbModel.Text, nudFuel.Value, (int)nudPrice.Value);
                lbCars.Items.Add(car);
                RecalculateList();
            }
        }
        
        private void RecalculateList() {
            if( lbCars.Items.Count > 0 ) {
                decimal avg = 0;
                Car mostEfficient = lbCars.Items[0] as Car;
                Car mostExpensive = lbCars.Items[0] as Car;
                foreach( Car item in lbCars.Items ) {
                    avg += item.FuelConsumption;
                    if( mostEfficient.FuelConsumption > item.FuelConsumption )
                        mostEfficient = item;
                    if( mostExpensive.Price < item.Price )
                        mostExpensive = item;
                }
                avg /= lbCars.Items.Count;
                tbAvgFuel.Text = $"{avg:#.##}";
                tbMostEfficient.Text = mostEfficient.ToString();
                tbMostExpensive.Text = mostExpensive.ToString();
            } else {
                tbAvgFuel.Text = String.Empty;
                tbMostEfficient.Text = String.Empty;
                tbMostExpensive.Text = String.Empty;
            }

        }
        private void btnAddMake_Click(object sender, EventArgs e) {
            NewMakeForm form = new NewMakeForm();
            if( form.ShowDialog() == DialogResult.OK ) {
                lbMakes.Items.Add(form.Make);
                cbMarka.Items.Add(form.Make);
            }
        }

        private void btnDelCar_Click(object sender, EventArgs e) {
            if(lbCars.Items.Count == 0) return;
            if( lbCars.SelectedIndex == -1 ) return;
            lbCars.Items.RemoveAt(lbCars.SelectedIndex);
            RecalculateList();
        }

        private void lbMakes_SelectedIndexChanged(object sender, EventArgs e) {
            cbMarka.SelectedItem = lbMakes.SelectedItem;
        }
    }
}
