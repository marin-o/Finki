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
    public partial class Form1 : Form {
        public Form1() {
            InitializeComponent();
        }

        private void btnDodadiNovTim_Click(object sender, EventArgs e) {
            TeamForm teamForm = new TeamForm();
            DialogResult result = teamForm.ShowDialog();
            if (result == DialogResult.OK) {
                lbTeams.Items.Add(teamForm.CreatedTeam);
            }
        }

        private void btnDodadiVoBilten_Click(object sender, EventArgs e) {
            if (lbTeams.SelectedItems.Count == 2 ) {
                Team team1 = lbTeams.SelectedItems[0] as Team;
                Team team2 = lbTeams.SelectedItems[1] as Team;
                if(team1.Country != team2.Country ) {
                    return;
                }
                Game game = new Game(mtbCode.Text, team1, team2, nud1.Value, nudX.Value, nud2.Value);
                lbGames.Items.Add(game);
                lbTeams.ClearSelected();
                nud1.Value = 1.0M;
                nudX.Value = 1.0M;
                nud2.Value = 1.0M;
                mtbCode.Clear();
            }
        }

        private void tbShifraNatprevar_TextChanged(object sender, EventArgs e) {
            lbGames.SelectedItems.Clear();
            String codeToSearch = tbShifraNatprevar.Text;
            for (int i=0; i<lbGames.Items.Count; i++) {
                Game game = lbGames.Items[i] as Game;
                if (game.Code == codeToSearch) {
                    lbGames.SelectedIndex = i;
                    return;
                }
            }   
        }

        private void btnAddGane_Click(object sender, EventArgs e) {
            if (lbGames.SelectedIndex != -1 && cbTim.SelectedIndex != -1) {
                Game game = lbGames.SelectedItem as Game;
                int type = cbTim.Text == "1" ? 0 : cbTim.Text == "x" ? 1 : 2;
                GameForTicket gameForTicket = new GameForTicket(game, type);
                lbTicket.Items.Add(gameForTicket);
                lbGames.ClearSelected();
                cbTim.SelectedIndex = -1;
                tbShifraNatprevar.Clear();
                tbKoeficient.Text = recalculateCoef().ToString();
                tbDobivka.Text = (recalculateCoef() * nudUplata.Value).ToString();
            }
        }

        private void Form1_Load(object sender, EventArgs e) {
            lbTeams.Items.Add(new Team("Real Madrid", "Spain"));
            lbTeams.Items.Add(new Team("Barcelona", "Spain"));
            lbTeams.Items.Add(new Team("Manchester United", "England"));
            lbTeams.Items.Add(new Team("Manchester City", "England"));
            lbTeams.Items.Add(new Team("Juventus", "Italy"));
            lbTeams.Items.Add(new Team("Inter", "Italy"));
            lbTeams.Items.Add(new Team("Bayern Munich", "Germany"));
            lbTeams.Items.Add(new Team("Borussia Dortmund", "Germany"));
            lbTeams.Items.Add(new Team("PSG", "France"));
            lbTeams.Items.Add(new Team("Lyon", "France"));
        }

        private void lbTicket_SizeChanged(object sender, EventArgs e) {

        }

        private decimal recalculateCoef() {
            decimal product = 1;
            for(int i=0; i<lbTicket.Items.Count; i++ ) {
                    GameForTicket gameForTicket = lbTicket.Items[i] as GameForTicket;
                    int tip = gameForTicket.Tip;
                    Game game = gameForTicket.Game;
                product *= tip == 0 ? game.Coef1 : tip == 1 ? game.CoefX : game.Coef2;
            }
            return product;
        }

        private void nudUplata_ValueChanged(object sender, EventArgs e) {
            if(lbTicket.Items.Count > 0)
                tbDobivka.Text = ( recalculateCoef() * nudUplata.Value ).ToString();
        }
    }
}
//how to disable github copilot
//https://stackoverflow.com/questions/68526881/how-to-disable-github-copilot