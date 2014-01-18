/*
* Copyright 2013-2014 Jérémie Faye, Nicolas Poelen, Roman Lopez, Alexis Delannoy
*
* This program is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free
* Software Foundation, either version 3 of the License, or (at your option) any
* later version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package batiment;

/**
 * Interface Stockable, pour les bâtiments possédant des stocks
 * @author Nicolas
 *
 */

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