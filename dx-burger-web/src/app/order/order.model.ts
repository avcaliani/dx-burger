export interface Order {
  id: number;
  total: number;
  discount: number;
  creationDate: Date;
  user: User;
  items: OrderItem[];
}

export interface OrderItem {
  id: number;
  price: number;
  discount: number;
  burger: Burger;
  ingredients: OrderIngredient[];
}

export interface OrderIngredient {
  id: number;
  quantity: number;
  ingredient: any;
}

export interface User {
  id: number;
  name: string;
  phone: string;
}

export interface Burger {
  id: number;
  name: string;
  price: number;
  ingredients: BurgerIngredient[];
}

export interface BurgerIngredient {
  quantity: number;
  ingredient: Ingredient;
}

export interface Ingredient {
  id: number;
  name: string;
  price: number;
}
