using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsPies {
    [Serializable] public class Circle {
        [NonSerialized] private static int initRadius = 30;
        public Color PrimaryColor { get; set; }
        public Color SecondaryColor { get; set; }
        public Point Center { get; set; }
        public int Radius { get; set; } = 30;

        public Circle(Color primaryColor, Point center) {
            PrimaryColor = primaryColor;
            Center = center;
            SecondaryColor = Color.FromArgb(255, 255 - PrimaryColor.R, 255 - PrimaryColor.G, 255 - PrimaryColor.B);
        }

        public void Draw(Graphics g) {
            Brush primary = new SolidBrush(PrimaryColor);
            Brush secondary= new SolidBrush(SecondaryColor);
            g.FillPie(primary,Center.X - Radius, Center.Y - Radius, 2*Radius,2*Radius, 180, 180);
            g.FillPie(secondary,Center.X - Radius, Center.Y - Radius, 2*Radius,2*Radius, 0, 180);
            primary.Dispose();
            secondary.Dispose();
        }

        public void Pulse() {
            Radius = (int)Math.Ceiling(0.1 * Radius + Radius);
            if(Radius > initRadius * 2)
                Radius = initRadius;
            Color temp = PrimaryColor;
            PrimaryColor = SecondaryColor;
            SecondaryColor = temp;
        }
    }
}
