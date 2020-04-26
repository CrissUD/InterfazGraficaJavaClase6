package app.client.components.productos;

public class ProductosComponent{

    private ProductosTemplate productosTemplate;

    public ProductosComponent(){
        productosTemplate = new ProductosTemplate(this);
    }

    public ProductosTemplate getProductosTemplate(){
        return productosTemplate;
    }
}