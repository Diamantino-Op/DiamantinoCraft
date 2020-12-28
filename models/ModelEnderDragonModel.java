
/**
 * EnderDragonModel - Either Mojang or a mod author (Taken From Memory) Created
 * using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public static class ModelEnderDragonModel<T extends Entity> extends EntityModel<T> {
	public ModelRenderer field_229067_j_;
	public ModelRenderer field_78219_b;
	public ModelRenderer field_78217_d;
	public ModelRenderer field_229075_u_;
	public ModelRenderer field_229078_x_;
	public ModelRenderer field_229065_h_;
	public ModelRenderer field_229073_p_;
	public ModelRenderer field_229070_m_;
	public ModelRenderer field_78221_a;
	public ModelRenderer field_229068_k_;
	public ModelRenderer field_229069_l_;
	public ModelRenderer field_229076_v_;
	public ModelRenderer field_229077_w_;
	public ModelRenderer field_229079_y_;
	public ModelRenderer field_229080_z_;
	public ModelRenderer field_229066_i_;
	public ModelRenderer field_229074_t_;
	public ModelRenderer field_229071_n_;
	public ModelRenderer field_229072_o_;
	public ModelRenderer field_78220_c;

	public ModelEnderDragonModel() {
		this.textureWidth = 256;
		this.textureHeight = 256;
		this.field_229080_z_ = new ModelRenderer(this, 112, 0);
		this.field_229080_z_.setRotationPoint(0.0F, 31.0F, 4.0F);
		this.field_229080_z_.addBox(-9.0F, 0.0F, -20.0F, 18.0F, 6.0F, 24.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229080_z_, 0.7517109732416818F, 0.0F, 0.0F);
		this.field_229071_n_ = new ModelRenderer(this, 196, 0);
		this.field_229071_n_.setRotationPoint(0.0F, 32.0F, -4.0F);
		this.field_229071_n_.addBox(-6.0F, -2.0F, 0.0F, 12.0F, 32.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229071_n_, 0.5017109536787238F, 0.0F, 0.0F);
		this.field_229072_o_ = new ModelRenderer(this, 112, 0);
		this.field_229072_o_.setRotationPoint(0.0F, 31.0F, 4.0F);
		this.field_229072_o_.addBox(-9.0F, 0.0F, -20.0F, 18.0F, 6.0F, 24.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229072_o_, 0.7517109732416818F, 0.0F, 0.0F);
		this.field_229068_k_ = new ModelRenderer(this, 226, 138);
		this.field_229068_k_.setRotationPoint(0.0F, 20.0F, -1.0F);
		this.field_229068_k_.addBox(-3.0F, -1.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229068_k_, -0.5017109536787238F, 0.0F, 0.0F);
		this.field_229067_j_ = new ModelRenderer(this, 112, 104);
		this.field_229067_j_.setRotationPoint(12.0F, 20.0F, 2.0F);
		this.field_229067_j_.addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229067_j_, 1.3017107965693995F, 0.0F, 0.0F);
		this.field_229065_h_ = new ModelRenderer(this, -56, 88);
		this.field_229065_h_.mirror = true;
		this.field_229065_h_.setRotationPoint(12.0F, 5.0F, 2.0F);
		this.field_229065_h_.setTextureOffset(168, 0).addBox(0.0F, -4.0F, -4.0F, 56.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.field_229065_h_.addBox(0.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229065_h_, -0.07500000087546035F, -0.2500000029182012F, -0.10000000116728046F);
		this.field_78219_b = new ModelRenderer(this, 48, 0);
		this.field_78219_b.setRotationPoint(9.507418E-6F, 24.378252F, 168.75218F);
		this.field_78219_b.setTextureOffset(144, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, 0.0F,
				0.0F);
		this.field_78219_b.addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_78219_b, 0.05922154123674346F, 3.141592653589793F, 0.0F);
		this.field_78221_a = new ModelRenderer(this, 0, 0);
		this.field_78221_a.setRotationPoint(0.0F, 36.42932F, -58.84613F);
		this.field_78221_a.setTextureOffset(176, 44).addBox(-6.0F, -1.0F, -24.0F, 12.0F, 5.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.field_78221_a.setTextureOffset(112, 30).addBox(-8.0F, -8.0F, -10.0F, 16.0F, 16.0F, 16.0F, 0.0F, 0.0F,
				0.0F);
		this.field_78221_a.addBox(-5.0F, -12.0F, -4.0F, 2.0F, 4.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.field_78221_a.setTextureOffset(112, 0).addBox(-5.0F, -3.0F, -22.0F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.field_78221_a.addBox(3.0F, -12.0F, -4.0F, 2.0F, 4.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.field_78221_a.setTextureOffset(112, 0).addBox(3.0F, -3.0F, -22.0F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_78221_a, 0.7853981633974483F, 0.0F, -0.0F);
		this.field_229073_p_ = new ModelRenderer(this, -56, 88);
		this.field_229073_p_.setRotationPoint(-12.0F, 5.0F, 2.0F);
		this.field_229073_p_.setTextureOffset(168, 0).addBox(-56.0F, -4.0F, -4.0F, 56.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.field_229073_p_.addBox(-56.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229073_p_, -0.07500000087546035F, 0.2500000029182012F, 0.10000000116728046F);
		this.field_229069_l_ = new ModelRenderer(this, 144, 104);
		this.field_229069_l_.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.field_229069_l_.addBox(-4.0F, 0.0F, -12.0F, 8.0F, 4.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229069_l_, 0.7517109732416818F, 0.0F, 0.0F);
		this.field_229078_x_ = new ModelRenderer(this, 0, 0);
		this.field_229078_x_.setRotationPoint(-16.0F, 16.0F, 42.0F);
		this.field_229078_x_.addBox(-8.0F, -4.0F, -8.0F, 16.0F, 32.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229078_x_, 1.0017108929360987F, 0.0F, 0.0F);
		this.field_229077_w_ = new ModelRenderer(this, 144, 104);
		this.field_229077_w_.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.field_229077_w_.addBox(-4.0F, 0.0F, -12.0F, 8.0F, 4.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229077_w_, 0.7517109732416818F, 0.0F, 0.0F);
		this.field_229066_i_ = new ModelRenderer(this, -56, 136);
		this.field_229066_i_.mirror = true;
		this.field_229066_i_.setRotationPoint(56.0F, 0.0F, 0.0F);
		this.field_229066_i_.setTextureOffset(168, 0).addBox(0.0F, -2.0F, -2.0F, 56.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.field_229066_i_.setTextureOffset(0, 8).addBox(0.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229066_i_, 0.0F, 0.0F, 1.056973083451897F);
		this.field_78217_d = new ModelRenderer(this, 0, 0);
		this.field_78217_d.setRotationPoint(0.0F, 4.0F, 8.0F);
		this.field_78217_d.addBox(-12.0F, 0.0F, -16.0F, 24.0F, 24.0F, 64.0F, 0.0F, 0.0F, 0.0F);
		this.field_78217_d.setTextureOffset(220, 53).addBox(-1.0F, -6.0F, -10.0F, 2.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.field_78217_d.setTextureOffset(220, 53).addBox(-1.0F, -6.0F, 10.0F, 2.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.field_78217_d.setTextureOffset(220, 53).addBox(-1.0F, -6.0F, 30.0F, 2.0F, 6.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.field_78220_c = new ModelRenderer(this, 176, 65);
		this.field_78220_c.setRotationPoint(0.0F, 4.0F, -8.0F);
		this.field_78220_c.addBox(-6.0F, 0.0F, -16.0F, 12.0F, 4.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_78220_c, 0.20000000233456092F, 0.0F, 0.0F);
		this.field_229070_m_ = new ModelRenderer(this, 0, 0);
		this.field_229070_m_.setRotationPoint(16.0F, 16.0F, 42.0F);
		this.field_229070_m_.addBox(-8.0F, -4.0F, -8.0F, 16.0F, 32.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229070_m_, 1.0017108929360987F, 0.0F, 0.0F);
		this.field_229074_t_ = new ModelRenderer(this, -56, 136);
		this.field_229074_t_.setRotationPoint(-56.0F, 0.0F, 0.0F);
		this.field_229074_t_.setTextureOffset(168, 0).addBox(-56.0F, -2.0F, -2.0F, 56.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.field_229074_t_.setTextureOffset(0, 8).addBox(-56.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229074_t_, 0.0F, 0.0F, -1.056973083451897F);
		this.field_229075_u_ = new ModelRenderer(this, 112, 104);
		this.field_229075_u_.setRotationPoint(-12.0F, 20.0F, 2.0F);
		this.field_229075_u_.addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229075_u_, 1.3017107965693995F, 0.0F, 0.0F);
		this.field_229079_y_ = new ModelRenderer(this, 196, 0);
		this.field_229079_y_.setRotationPoint(0.0F, 32.0F, -4.0F);
		this.field_229079_y_.addBox(-6.0F, -2.0F, 0.0F, 12.0F, 32.0F, 12.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229079_y_, 0.5017109536787238F, 0.0F, 0.0F);
		this.field_229076_v_ = new ModelRenderer(this, 226, 138);
		this.field_229076_v_.setRotationPoint(0.0F, 20.0F, -1.0F);
		this.field_229076_v_.addBox(-3.0F, -1.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(field_229076_v_, -0.5017109536787238F, 0.0F, 0.0F);
		this.field_229079_y_.addChild(this.field_229080_z_);
		this.field_229070_m_.addChild(this.field_229071_n_);
		this.field_229071_n_.addChild(this.field_229072_o_);
		this.field_229067_j_.addChild(this.field_229068_k_);
		this.field_229068_k_.addChild(this.field_229069_l_);
		this.field_229076_v_.addChild(this.field_229077_w_);
		this.field_229065_h_.addChild(this.field_229066_i_);
		this.field_78221_a.addChild(this.field_78220_c);
		this.field_229073_p_.addChild(this.field_229074_t_);
		this.field_229078_x_.addChild(this.field_229079_y_);
		this.field_229075_u_.addChild(this.field_229076_v_);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.field_229067_j_, this.field_229065_h_, this.field_78219_b, this.field_78221_a,
				this.field_229073_p_, this.field_229078_x_, this.field_78217_d, this.field_229070_m_,
				this.field_229075_u_).forEach((modelRenderer) -> {
					modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue,
							alpha);
				});
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
