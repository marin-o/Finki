using EShop.Domain.Domain;
using EShop.Domain.DTO;
using EShop.Service.Interface;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.CodeAnalysis.CSharp.Syntax;
using Movie_App.Service.Interface;
using Stripe;

namespace EShop.Web.Controllers.API
{
    [Route("api/[controller]")]
    [ApiController]
    public class AdminController : ControllerBase
    {
        private readonly IOrderService _orderService;
        private readonly IMovieService _movieService;
        public AdminController(IOrderService orderService, IMovieService movieService)
        {
            _orderService = orderService;
            _movieService = movieService;
        }

        [HttpGet]
        public List<Order> Index()
        {
            return _orderService.GetAllOrders();
        }

        [HttpGet("[action]")]
        public List<Order> GetAllOrders()
        {
            return _orderService.GetAllOrders();
        }

        [HttpPost("[action]")]
        public Order GetDetailsForOrder(BaseEntity model)
        {
            return _orderService.GetDetailsForOrder(model);
        }

        [HttpPost("[action]")]
        public bool ImportAllMovies(List<ImportMoviesDTO> model)
        {
            bool status = true;
            var allMovies = _movieService.GetAllMovies();
            foreach (var item in model)
            {
                var movieCheck = allMovies.Find(x => x.MovieName == item.MovieName);
                if (movieCheck == null)
                {
                    var newMovie = new Movie
                    {
                        MovieName = item.MovieName,
                        MovieDescription = item.MovieDescription,
                        MovieImage = item.MovieImage,
                        Rating = item.Rating,
                        Tickets = new List<Ticket>()
                    };
                    _movieService.CreateNewMovie(newMovie);

                }
                else continue;
            }
            return status;
        }
    }
}
