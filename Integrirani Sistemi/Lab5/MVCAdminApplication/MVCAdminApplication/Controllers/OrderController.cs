using GemBox.Document;
using Microsoft.AspNetCore.Mvc;
using MVCAdminApplication.Models;
using Newtonsoft.Json;
using System.Text;

namespace MVCAdminApplication.Controllers
{
    public class OrderController : Controller
    {
        public OrderController()
        {
            ComponentInfo.SetLicense("FREE-LIMITED-KEY");
        }

        public IActionResult Index()
        {
            HttpClient client = new HttpClient();
            string URL = "https://localhost:44341/api/Admin/GetAllOrders";
            HttpResponseMessage response = client.GetAsync(URL).Result;

            var data = response.Content.ReadAsAsync<List<Order>>().Result;
            return View(data);
        }

        public IActionResult Details(Guid Id)
        {
            HttpClient client = new HttpClient();
            string URL = "https://localhost:44341/api/Admin/GetDetailsForOrder";
            var model = new
            {
                Id = Id
            };
            HttpContent content = new StringContent(JsonConvert.SerializeObject(model), Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.PostAsync(URL, content).Result;

            var data = response.Content.ReadAsAsync<Order>().Result;
            return View(data);
        }

        public FileContentResult CreateInvoice(Guid Id)
        {
            HttpClient client = new HttpClient();
            string URL = "https://localhost:44341/api/Admin/GetDetailsForOrder";
            var model = new
            {
                Id = Id
            };
            HttpContent content = new StringContent(JsonConvert.SerializeObject(model), Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.PostAsync(URL, content).Result;

            var data = response.Content.ReadAsAsync<Order>().Result;

            var templatePath = Path.Combine(Directory.GetCurrentDirectory(), "Invoice.docx");
            var document = DocumentModel.Load(templatePath);
            document.Content.Replace("{{OrderNumber}}", data.Id.ToString());
            document.Content.Replace("{{UserName}}", data.Owner.FirstName + " " + data.Owner.LastName);
            StringBuilder sb = new StringBuilder();
            var totalPrice = 0.0;
            foreach (var item in data.ProductInOrders)
            {
                sb.AppendLine(item?.OrderedProduct?.Movie?.MovieName + " - " + item?.Quantity + " - " + item?.OrderedProduct?.Price);
                totalPrice += item.Quantity * item.OrderedProduct.Price;
            }
            document.Content.Replace("{{ProductList}}", sb.ToString());
            document.Content.Replace("{{TotalPrice}}", totalPrice.ToString());

            var stream = new MemoryStream();
            document.Save(stream, new PdfSaveOptions());

            return File(stream.ToArray(), new PdfSaveOptions().ContentType, "ExportedInvoice.pdf");
        }

        public IActionResult CreateAllInvoices()
        {
            HttpClient client = new HttpClient();
            string URL = "https://localhost:44341/api/Admin/GetAllOrders";
            HttpResponseMessage response = client.GetAsync(URL).Result;

            var orders = response.Content.ReadAsAsync<List<Order>>().Result;

            foreach (var order in orders)
            {
                var templatePath = Path.Combine(Directory.GetCurrentDirectory(), "Invoice.docx");
                var document = DocumentModel.Load(templatePath);

                StringBuilder sb = new StringBuilder();
                var totalPrice = 0.0;
                sb.AppendLine("Order Number: " + order.Id.ToString());
                sb.AppendLine("User: " + order.Owner.FirstName + " " + order.Owner.LastName);
                foreach (var item in order.ProductInOrders)
                {
                    sb.AppendLine(item?.OrderedProduct?.Movie?.MovieName + " - " + item?.Quantity + " - " + item?.OrderedProduct?.Price);
                    totalPrice += item.Quantity * item.OrderedProduct.Price;
                }
                sb.AppendLine("Order Total: " + totalPrice.ToString());

                document.Content.Replace("{{OrderNumber}}", order.Id.ToString());
                document.Content.Replace("{{UserName}}", order.Owner.FirstName + " " + order.Owner.LastName);
                document.Content.Replace("{{ProductList}}", sb.ToString());
                document.Content.Replace("{{TotalPrice}}", totalPrice.ToString());

                var stream = new MemoryStream();
                document.Save(stream, new PdfSaveOptions());

                var fileName = $"ExportedInvoice_{order.Id}.pdf";
                System.IO.File.WriteAllBytes(fileName, stream.ToArray());
            }

            return Ok("Invoices created successfully.");
        }
    }
}
