using ExcelDataReader;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Text;
using VezbanjeAdminApp.Models;

namespace VezbanjeAdminApp.Controllers
{
    public class PatientsController : Controller
    {
        //public UserManager<EShopApplicationUser> usermanager;
        //public UserController(UserManager<EShopApplicationUser> usermanager)
        //{
        //    this.usermanager = usermanager;
        //}
        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public IActionResult ImportUsers(IFormFile file)
        {
            string pathToUpload = $"{Directory.GetCurrentDirectory()}\\files\\{file.FileName}";

            using (FileStream fileStream = System.IO.File.Create(pathToUpload))
            {
                file.CopyTo(fileStream);
                fileStream.Flush();
            }

            List<Patient> users = getAllUsersFromFile(file.FileName);
            HttpClient client = new HttpClient();
            string URL = "https://localhost:44331/api/Admin/ImportAllPatients";

            HttpContent content = new StringContent(JsonConvert.SerializeObject(users), Encoding.UTF8, "application/json");

            HttpResponseMessage response = client.PostAsync(URL, content).Result;

            var result = response.Content.ReadAsAsync<bool>().Result;

            return RedirectToAction("Index", "Order");

        }

        private List<Patient> getAllUsersFromFile(string fileName)
        {
            List<Patient> users = new List<Patient>();
            string filePath = $"{Directory.GetCurrentDirectory()}\\files\\{fileName}";

            System.Text.Encoding.RegisterProvider(System.Text.CodePagesEncodingProvider.Instance);

            using (var stream = System.IO.File.Open(filePath, FileMode.Open, FileAccess.Read))
            {
                using (var reader = ExcelReaderFactory.CreateReader(stream))
                {
                    while (reader.Read())
                    {
                        users.Add(new Patient
                        {
                            FirstName = reader.GetValue(0).ToString(),
                            LastName = reader.GetValue(1).ToString(),
                            PhoneNumber = reader.GetValue(2).ToString(),
                            Email = reader.GetValue(3).ToString(),
                            Embg = reader.GetValue(4).ToString()
                        });
                    }

                }
            }
            return users;

        }
    }
}
