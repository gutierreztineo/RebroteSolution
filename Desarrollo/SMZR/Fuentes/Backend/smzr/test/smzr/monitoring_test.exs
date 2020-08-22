defmodule Smzr.MonitoringTest do
  use Smzr.DataCase

  alias Smzr.Monitoring

  describe "ailments" do
    alias Smzr.Monitoring.Ailment

    @valid_attrs %{description: "some description"}
    @update_attrs %{description: "some updated description"}
    @invalid_attrs %{description: nil}

    def ailment_fixture(attrs \\ %{}) do
      {:ok, ailment} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Monitoring.create_ailment()

      ailment
    end

    test "list_ailments/0 returns all ailments" do
      ailment = ailment_fixture()
      assert Monitoring.list_ailments() == [ailment]
    end

    test "get_ailment!/1 returns the ailment with given id" do
      ailment = ailment_fixture()
      assert Monitoring.get_ailment!(ailment.id) == ailment
    end

    test "create_ailment/1 with valid data creates a ailment" do
      assert {:ok, %Ailment{} = ailment} = Monitoring.create_ailment(@valid_attrs)
      assert ailment.description == "some description"
    end

    test "create_ailment/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Monitoring.create_ailment(@invalid_attrs)
    end

    test "update_ailment/2 with valid data updates the ailment" do
      ailment = ailment_fixture()
      assert {:ok, %Ailment{} = ailment} = Monitoring.update_ailment(ailment, @update_attrs)
      assert ailment.description == "some updated description"
    end

    test "update_ailment/2 with invalid data returns error changeset" do
      ailment = ailment_fixture()
      assert {:error, %Ecto.Changeset{}} = Monitoring.update_ailment(ailment, @invalid_attrs)
      assert ailment == Monitoring.get_ailment!(ailment.id)
    end

    test "delete_ailment/1 deletes the ailment" do
      ailment = ailment_fixture()
      assert {:ok, %Ailment{}} = Monitoring.delete_ailment(ailment)
      assert_raise Ecto.NoResultsError, fn -> Monitoring.get_ailment!(ailment.id) end
    end

    test "change_ailment/1 returns a ailment changeset" do
      ailment = ailment_fixture()
      assert %Ecto.Changeset{} = Monitoring.change_ailment(ailment)
    end
  end

  describe "profile_ailments" do
    alias Smzr.Monitoring.ProfileAilment

    @valid_attrs %{}
    @update_attrs %{}
    @invalid_attrs %{}

    def profile_ailment_fixture(attrs \\ %{}) do
      {:ok, profile_ailment} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Monitoring.create_profile_ailment()

      profile_ailment
    end

    test "list_profile_ailments/0 returns all profile_ailments" do
      profile_ailment = profile_ailment_fixture()
      assert Monitoring.list_profile_ailments() == [profile_ailment]
    end

    test "get_profile_ailment!/1 returns the profile_ailment with given id" do
      profile_ailment = profile_ailment_fixture()
      assert Monitoring.get_profile_ailment!(profile_ailment.id) == profile_ailment
    end

    test "create_profile_ailment/1 with valid data creates a profile_ailment" do
      assert {:ok, %ProfileAilment{} = profile_ailment} = Monitoring.create_profile_ailment(@valid_attrs)
    end

    test "create_profile_ailment/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Monitoring.create_profile_ailment(@invalid_attrs)
    end

    test "update_profile_ailment/2 with valid data updates the profile_ailment" do
      profile_ailment = profile_ailment_fixture()
      assert {:ok, %ProfileAilment{} = profile_ailment} = Monitoring.update_profile_ailment(profile_ailment, @update_attrs)
    end

    test "update_profile_ailment/2 with invalid data returns error changeset" do
      profile_ailment = profile_ailment_fixture()
      assert {:error, %Ecto.Changeset{}} = Monitoring.update_profile_ailment(profile_ailment, @invalid_attrs)
      assert profile_ailment == Monitoring.get_profile_ailment!(profile_ailment.id)
    end

    test "delete_profile_ailment/1 deletes the profile_ailment" do
      profile_ailment = profile_ailment_fixture()
      assert {:ok, %ProfileAilment{}} = Monitoring.delete_profile_ailment(profile_ailment)
      assert_raise Ecto.NoResultsError, fn -> Monitoring.get_profile_ailment!(profile_ailment.id) end
    end

    test "change_profile_ailment/1 returns a profile_ailment changeset" do
      profile_ailment = profile_ailment_fixture()
      assert %Ecto.Changeset{} = Monitoring.change_profile_ailment(profile_ailment)
    end
  end

  describe "ailment_levels" do
    alias Smzr.Monitoring.AilmentLevel

    @valid_attrs %{description: "some description", level: 42}
    @update_attrs %{description: "some updated description", level: 43}
    @invalid_attrs %{description: nil, level: nil}

    def ailment_level_fixture(attrs \\ %{}) do
      {:ok, ailment_level} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Monitoring.create_ailment_level()

      ailment_level
    end

    test "list_ailment_levels/0 returns all ailment_levels" do
      ailment_level = ailment_level_fixture()
      assert Monitoring.list_ailment_levels() == [ailment_level]
    end

    test "get_ailment_level!/1 returns the ailment_level with given id" do
      ailment_level = ailment_level_fixture()
      assert Monitoring.get_ailment_level!(ailment_level.id) == ailment_level
    end

    test "create_ailment_level/1 with valid data creates a ailment_level" do
      assert {:ok, %AilmentLevel{} = ailment_level} = Monitoring.create_ailment_level(@valid_attrs)
      assert ailment_level.description == "some description"
      assert ailment_level.level == 42
    end

    test "create_ailment_level/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Monitoring.create_ailment_level(@invalid_attrs)
    end

    test "update_ailment_level/2 with valid data updates the ailment_level" do
      ailment_level = ailment_level_fixture()
      assert {:ok, %AilmentLevel{} = ailment_level} = Monitoring.update_ailment_level(ailment_level, @update_attrs)
      assert ailment_level.description == "some updated description"
      assert ailment_level.level == 43
    end

    test "update_ailment_level/2 with invalid data returns error changeset" do
      ailment_level = ailment_level_fixture()
      assert {:error, %Ecto.Changeset{}} = Monitoring.update_ailment_level(ailment_level, @invalid_attrs)
      assert ailment_level == Monitoring.get_ailment_level!(ailment_level.id)
    end

    test "delete_ailment_level/1 deletes the ailment_level" do
      ailment_level = ailment_level_fixture()
      assert {:ok, %AilmentLevel{}} = Monitoring.delete_ailment_level(ailment_level)
      assert_raise Ecto.NoResultsError, fn -> Monitoring.get_ailment_level!(ailment_level.id) end
    end

    test "change_ailment_level/1 returns a ailment_level changeset" do
      ailment_level = ailment_level_fixture()
      assert %Ecto.Changeset{} = Monitoring.change_ailment_level(ailment_level)
    end
  end

  describe "risks" do
    alias Smzr.Monitoring.Risk

    @valid_attrs %{descripcion: "some descripcion", name: "some name"}
    @update_attrs %{descripcion: "some updated descripcion", name: "some updated name"}
    @invalid_attrs %{descripcion: nil, name: nil}

    def risk_fixture(attrs \\ %{}) do
      {:ok, risk} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Monitoring.create_risk()

      risk
    end

    test "list_risks/0 returns all risks" do
      risk = risk_fixture()
      assert Monitoring.list_risks() == [risk]
    end

    test "get_risk!/1 returns the risk with given id" do
      risk = risk_fixture()
      assert Monitoring.get_risk!(risk.id) == risk
    end

    test "create_risk/1 with valid data creates a risk" do
      assert {:ok, %Risk{} = risk} = Monitoring.create_risk(@valid_attrs)
      assert risk.descripcion == "some descripcion"
      assert risk.name == "some name"
    end

    test "create_risk/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Monitoring.create_risk(@invalid_attrs)
    end

    test "update_risk/2 with valid data updates the risk" do
      risk = risk_fixture()
      assert {:ok, %Risk{} = risk} = Monitoring.update_risk(risk, @update_attrs)
      assert risk.descripcion == "some updated descripcion"
      assert risk.name == "some updated name"
    end

    test "update_risk/2 with invalid data returns error changeset" do
      risk = risk_fixture()
      assert {:error, %Ecto.Changeset{}} = Monitoring.update_risk(risk, @invalid_attrs)
      assert risk == Monitoring.get_risk!(risk.id)
    end

    test "delete_risk/1 deletes the risk" do
      risk = risk_fixture()
      assert {:ok, %Risk{}} = Monitoring.delete_risk(risk)
      assert_raise Ecto.NoResultsError, fn -> Monitoring.get_risk!(risk.id) end
    end

    test "change_risk/1 returns a risk changeset" do
      risk = risk_fixture()
      assert %Ecto.Changeset{} = Monitoring.change_risk(risk)
    end
  end

  describe "profile_risks" do
    alias Smzr.Monitoring.ProfileRisk

    @valid_attrs %{}
    @update_attrs %{}
    @invalid_attrs %{}

    def profile_risk_fixture(attrs \\ %{}) do
      {:ok, profile_risk} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Monitoring.create_profile_risk()

      profile_risk
    end

    test "list_profile_risks/0 returns all profile_risks" do
      profile_risk = profile_risk_fixture()
      assert Monitoring.list_profile_risks() == [profile_risk]
    end

    test "get_profile_risk!/1 returns the profile_risk with given id" do
      profile_risk = profile_risk_fixture()
      assert Monitoring.get_profile_risk!(profile_risk.id) == profile_risk
    end

    test "create_profile_risk/1 with valid data creates a profile_risk" do
      assert {:ok, %ProfileRisk{} = profile_risk} = Monitoring.create_profile_risk(@valid_attrs)
    end

    test "create_profile_risk/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Monitoring.create_profile_risk(@invalid_attrs)
    end

    test "update_profile_risk/2 with valid data updates the profile_risk" do
      profile_risk = profile_risk_fixture()
      assert {:ok, %ProfileRisk{} = profile_risk} = Monitoring.update_profile_risk(profile_risk, @update_attrs)
    end

    test "update_profile_risk/2 with invalid data returns error changeset" do
      profile_risk = profile_risk_fixture()
      assert {:error, %Ecto.Changeset{}} = Monitoring.update_profile_risk(profile_risk, @invalid_attrs)
      assert profile_risk == Monitoring.get_profile_risk!(profile_risk.id)
    end

    test "delete_profile_risk/1 deletes the profile_risk" do
      profile_risk = profile_risk_fixture()
      assert {:ok, %ProfileRisk{}} = Monitoring.delete_profile_risk(profile_risk)
      assert_raise Ecto.NoResultsError, fn -> Monitoring.get_profile_risk!(profile_risk.id) end
    end

    test "change_profile_risk/1 returns a profile_risk changeset" do
      profile_risk = profile_risk_fixture()
      assert %Ecto.Changeset{} = Monitoring.change_profile_risk(profile_risk)
    end
  end

  describe "advices" do
    alias Smzr.Monitoring.Advice

    @valid_attrs %{description: "some description"}
    @update_attrs %{description: "some updated description"}
    @invalid_attrs %{description: nil}

    def advice_fixture(attrs \\ %{}) do
      {:ok, advice} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Monitoring.create_advice()

      advice
    end

    test "list_advices/0 returns all advices" do
      advice = advice_fixture()
      assert Monitoring.list_advices() == [advice]
    end

    test "get_advice!/1 returns the advice with given id" do
      advice = advice_fixture()
      assert Monitoring.get_advice!(advice.id) == advice
    end

    test "create_advice/1 with valid data creates a advice" do
      assert {:ok, %Advice{} = advice} = Monitoring.create_advice(@valid_attrs)
      assert advice.description == "some description"
    end

    test "create_advice/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Monitoring.create_advice(@invalid_attrs)
    end

    test "update_advice/2 with valid data updates the advice" do
      advice = advice_fixture()
      assert {:ok, %Advice{} = advice} = Monitoring.update_advice(advice, @update_attrs)
      assert advice.description == "some updated description"
    end

    test "update_advice/2 with invalid data returns error changeset" do
      advice = advice_fixture()
      assert {:error, %Ecto.Changeset{}} = Monitoring.update_advice(advice, @invalid_attrs)
      assert advice == Monitoring.get_advice!(advice.id)
    end

    test "delete_advice/1 deletes the advice" do
      advice = advice_fixture()
      assert {:ok, %Advice{}} = Monitoring.delete_advice(advice)
      assert_raise Ecto.NoResultsError, fn -> Monitoring.get_advice!(advice.id) end
    end

    test "change_advice/1 returns a advice changeset" do
      advice = advice_fixture()
      assert %Ecto.Changeset{} = Monitoring.change_advice(advice)
    end
  end

  describe "ailment_advices" do
    alias Smzr.Monitoring.AilmentAdvice

    @valid_attrs %{}
    @update_attrs %{}
    @invalid_attrs %{}

    def ailment_advice_fixture(attrs \\ %{}) do
      {:ok, ailment_advice} =
        attrs
        |> Enum.into(@valid_attrs)
        |> Monitoring.create_ailment_advice()

      ailment_advice
    end

    test "list_ailment_advices/0 returns all ailment_advices" do
      ailment_advice = ailment_advice_fixture()
      assert Monitoring.list_ailment_advices() == [ailment_advice]
    end

    test "get_ailment_advice!/1 returns the ailment_advice with given id" do
      ailment_advice = ailment_advice_fixture()
      assert Monitoring.get_ailment_advice!(ailment_advice.id) == ailment_advice
    end

    test "create_ailment_advice/1 with valid data creates a ailment_advice" do
      assert {:ok, %AilmentAdvice{} = ailment_advice} = Monitoring.create_ailment_advice(@valid_attrs)
    end

    test "create_ailment_advice/1 with invalid data returns error changeset" do
      assert {:error, %Ecto.Changeset{}} = Monitoring.create_ailment_advice(@invalid_attrs)
    end

    test "update_ailment_advice/2 with valid data updates the ailment_advice" do
      ailment_advice = ailment_advice_fixture()
      assert {:ok, %AilmentAdvice{} = ailment_advice} = Monitoring.update_ailment_advice(ailment_advice, @update_attrs)
    end

    test "update_ailment_advice/2 with invalid data returns error changeset" do
      ailment_advice = ailment_advice_fixture()
      assert {:error, %Ecto.Changeset{}} = Monitoring.update_ailment_advice(ailment_advice, @invalid_attrs)
      assert ailment_advice == Monitoring.get_ailment_advice!(ailment_advice.id)
    end

    test "delete_ailment_advice/1 deletes the ailment_advice" do
      ailment_advice = ailment_advice_fixture()
      assert {:ok, %AilmentAdvice{}} = Monitoring.delete_ailment_advice(ailment_advice)
      assert_raise Ecto.NoResultsError, fn -> Monitoring.get_ailment_advice!(ailment_advice.id) end
    end

    test "change_ailment_advice/1 returns a ailment_advice changeset" do
      ailment_advice = ailment_advice_fixture()
      assert %Ecto.Changeset{} = Monitoring.change_ailment_advice(ailment_advice)
    end
  end
end
