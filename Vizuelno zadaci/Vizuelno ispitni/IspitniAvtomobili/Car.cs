using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniAvtomobili {
    public class Car {
        public Make Make { get; set; }
        public string Model { get; set; }
        public decimal FuelConsumption { get; set; }
        public int Price { get; set; }

        public Car(Make make, string model, decimal fuelConsumption, int price) {
            Make = make;
            Model = model;
            FuelConsumption = fuelConsumption;
            Price = price;
        }

        public override string ToString() {
            return $"{Make.Name} {Model} {FuelConsumption} {Price}";
        }
    }
}
