using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IspitniPolygons {
    [Serializable] public class Scene {
        public List<Polygon> Polygons { get; set; } = new List<Polygon>();
        public Color PolygonColor { get; set; } = Color.Blue;
        public Polygon CurrentPolygon { get; set; }
        public Scene() {
            CurrentPolygon = new Polygon(PolygonColor);
        }
        
        public void Draw(Graphics g) {
            foreach (Polygon p in Polygons) {
                p.Draw(g);
            }
            CurrentPolygon.Draw(g);
        }

        public void AddPoint(Point point) {
            CurrentPolygon.AddPoint(point);
            if(CurrentPolygon.IsClosed) {
                Polygons.Add(CurrentPolygon);
                CurrentPolygon = new Polygon(PolygonColor);
            }
        }
        public void UpdateCursor(Point cursor) {
            CurrentPolygon.MoveCursor(cursor);
        }

        public void UpdateColor(Color color) {
            PolygonColor = color;
            CurrentPolygon.Color = color;
        }
        public void Move(Keys keycode) {
            foreach(Polygon p in Polygons) {
                p.Move(keycode);
            }
        }
    }
}
