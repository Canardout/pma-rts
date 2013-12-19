package batiment;

public interface Stockable {
        public int getStock ();
        /**
         *
         * @param demande
         * @return la quantité donné
         */
        public int prendreStock (int demande);
        /**
         *
         * @param dons
         * @return la quantité qui n'a pas pu être stocké
         */
        public int donnerStock (int dons);
}