using BA.Domain.Models;
using BA.Repository.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BA.Service.Implementation
{
    public class ReservationService
    {
        private readonly IRepository<Reservation> _productRepository;
        private readonly IRepository<BookReservation> _bookReservationRepository;
        //private readonly IUserRepository _userRepository;

        public ReservationService(IRepository<Reservation> productRepository, IRepository<BookReservation> productInShoppingCartRepository/*, IUserRepository userRepository*/)
        {
            _productRepository = productRepository;
            _bookReservationRepository = productInShoppingCartRepository;
            //_userRepository = userRepository;
        }

        public void CreateNewProduct(Reservation p)
        {
            _productRepository.Insert(p);
        }

        public void DeleteProduct(Guid id)
        {
            var product = _productRepository.Get(id);
            _productRepository.Delete(product);
        }

        public List<Reservation> GetAllProducts()
        {
            return _productRepository.GetAll().ToList();
        }

        public Reservation GetDetailsForProduct(Guid? id)
        {
            var product = _productRepository.Get(id);
            return product;
        }

        public void UpdateExistingProduct(Reservation p)
        {
            _productRepository.Update(p);
        }
    }
}
